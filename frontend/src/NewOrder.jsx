import { Box, Button, CircularProgress, IconButton, TextField } from "@mui/material";
import { Delete as DeleteIcon, Send as SendIcon } from '@mui/icons-material'
import { Controller, useFieldArray, useForm } from "react-hook-form";
import { useMutation } from "react-query";
import { useNavigate } from "react-router-dom";
import { api } from "./api.js";
import { Autocomplete } from "./components/Autocomplete.jsx";
import { PageHeader, PageHeaderActions, PageHeaderText } from "./components/PageHeader.jsx";

async function loadCategoryOptions() {
    const categories = await api.get('categories').json();
    return categories.map(c => ({
        id: c.id,
        label: c.name,
    }));
}

async function loadProductOptions(categoryId) {
    const categories = await api.get(`products?categoryId=${categoryId}`).json();
    return categories.map(c => ({
        id: c.id,
        label: c.name,
        price: c.price,
    }));
}

function safeParseInt(x) {
    if (typeof x === 'number') {
        return x;
    }

    const value = parseInt(x, 10);

    if (!value) {
        return 0;
    }

    return value;
}

function coalesceMultiply(x, y) {
    return safeParseInt(x) * safeParseInt(y);
}

function toCurrencyString(x) {
    const intValue = safeParseInt(x);

    if (intValue === 0) {
        return '-';
    }

    return new Intl.NumberFormat('lt-LT', { style: 'currency', currency: 'EUR' }).format(intValue);
}

function OrderRow(props) {
    const { control, index, items, remove } = props;

    return (
        <>
            <Box>
                <Controller
                    control={control}
                    name={`items.${index}.categoryOption`}
                    render={({ field }) => (
                        <Autocomplete
                            loadOptions={loadCategoryOptions}
                            label="Category"
                            {...field}
                        />
                    )}
                />
            </Box>
            <Box>
                <Controller
                    control={control}
                    name={`items.${index}.productOption`}
                    render={({ field }) => (
                        <Autocomplete
                            loadOptions={() => loadProductOptions(items[index].categoryOption?.id)}
                            label="Product"
                            disabled={items[index].categoryOption === null}
                            {...field}
                        />
                    )}
                />
            </Box>
            <Box>
                <TextField
                    label="Price"
                    value={toCurrencyString(items[index].productOption?.price)}
                    inputProps={{ readOnly: true }}
                />
            </Box>
            <Box>
                <Controller
                    control={control}
                    name={`items.${index}.quantity`}
                    render={({ field }) => (
                        <TextField
                            label="Quantity"
                            type="number"
                            disabled={items[index].productOption === null}
                            inputProps={{
                                step: 1,
                            }}
                            {...field}
                        />
                    )}
                />
            </Box>
            <Box>
                <TextField
                    label="Total"
                    value={toCurrencyString(coalesceMultiply(items[index].productOption?.price, items[index].quantity))}
                    inputProps={{ readOnly: true }}
                />
            </Box>
            <Box>
                <IconButton aria-label="delete" color="error" onClick={() => remove(index)}>
                    <DeleteIcon />
                </IconButton>
            </Box>
        </>
    );
}

function createOrder(formValues) {
    const dto = {
        items: formValues.items.map(item => ({
            productId: item.productOption.id,
            quantity: parseInt(item.quantity, 10),
        })),
    };

    return api.post('orders', { json: dto }).text();
}

export function NewOrder() {
    const { control, watch, handleSubmit  } = useForm({
        defaultValues: {
            items: [
                { categoryOption: null, productOption: null, quantity: '1' },
            ],
        },
    });
    const { fields, append, remove } = useFieldArray({
        control,
        name: 'items',
    });
    const { items } = watch();

    const navigate = useNavigate();

    const { isLoading, mutate } = useMutation({
        mutationFn: createOrder,
        onSuccess: id => navigate(`/orders/${id}`),
    });

    const handleValidSubmit = data => {
        mutate(data);
    }

    return (
        <Box>
            <PageHeader>
                <PageHeaderText>New Order</PageHeaderText>
                <PageHeaderActions>
                    {isLoading && <CircularProgress size={24} />}
                    <Button
                        onClick={handleSubmit(handleValidSubmit)}
                        variant="contained"
                        endIcon={<SendIcon />}
                        disabled={isLoading}
                        sx={{ ml: 1 }}
                        disableElevation
                    >
                        Create order
                    </Button>
                </PageHeaderActions>
            </PageHeader>
            <Box
                sx={{
                    py: 2,
                    display: 'grid',
                    gridTemplateColumns: '1fr 1fr auto 120px auto auto',
                    columnGap: 2,
                    rowGap: 2,
                    alignItems: 'center',
                }}
            >
                {fields.map((field, index) => (
                    <OrderRow key={field.id} control={control} index={index} items={items} remove={remove} />
                ))}
            </Box>
            <Box display="flex" justifyContent="center">
                <Button
                    onClick={() => append({ categoryOption: null, productOption: null, quantity: '1' }, { shouldFocus: false })}
                >
                    Add item
                </Button>
            </Box>
        </Box>
    );
}