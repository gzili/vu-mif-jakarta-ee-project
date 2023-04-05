import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    TextField
} from "@mui/material";
import { useEffect, useRef, useState } from "react";
import { Form, redirect, useActionData, useLoaderData, useSubmit } from "react-router-dom";
import { v4 as uuid } from 'uuid';
import { api } from "./api.js";
import { PageHeader, PageHeaderActions, PageHeaderText } from "./components/PageHeader.jsx";

export async function editProductAction({ params, request }) {
    const fd = await request.formData();
    const dto = {
        name: fd.get('productName'),
        price: parseFloat(fd.get('productPrice')),
        version: parseInt(fd.get('productVersion')),
    }
    const force = fd.get('force');
    let url = `products/${params.id}`;
    if (force) {
        url += `?force=${force}`;
    }
    try {
        await api.put(url, { json: dto });
    } catch (e) {
        return uuid();
    }
    return redirect('/products');
}

export function ProductEdit() {
    const product = useLoaderData();

    const [open, setOpen] = useState(false);

    const submit = useSubmit();

    const formRef = useRef();

    const handleOverwrite = () => {
        const fd = new FormData(formRef.current);
        fd.set("force", "true");
        submit(fd, { method: formRef.current.method, action: formRef.current.action });
    }

    const conflict = useActionData();
    const previousConflict = useRef(conflict);

    useEffect(() => {
        if (conflict !== previousConflict.current) {
            previousConflict.current = conflict;
            setOpen(true);
        }
    }, [conflict])

    return (
        <Box>
            <Form ref={formRef} action={`/products/${product.id}/edit`} method="PUT">
                <PageHeader>
                    <PageHeaderText>Edit product</PageHeaderText>
                    <PageHeaderActions>
                        <Button variant="contained" type="submit">Save changes</Button>
                    </PageHeaderActions>
                </PageHeader>
                <Box maxWidth={500} py={2} display="flex" flexDirection="column" sx={{ '& > :not(:last-child)': { mb: 2 } }}>
                    <TextField name="productName" label="Name" defaultValue={product.name} />
                    <TextField name="productPrice" label="Price" defaultValue={`${product.price}`} type="number" />
                    <input type="hidden" name="productVersion" value={`${product.version}`} />
                </Box>
            </Form>
            <Dialog open={open}>
                <DialogTitle>Update Conflict</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        The product details have been modified while you were editing. Do you want to overwrite the changes?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpen(false)}>Cancel</Button>
                    <Button onClick={handleOverwrite} color="error">Overwrite</Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
}