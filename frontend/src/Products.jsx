import {
    Box,
    Chip,
    IconButton,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import { Link, useLoaderData } from "react-router-dom";
import { api } from "./api.js";
import { PageHeader, PageHeaderText } from "./components/PageHeader.jsx";
import { toCurrency } from "./utils.js";

export function productsLoader() {
    return api.get('products').json();
}

function ProductRow(props) {
    const { product } = props;

    return (
        <TableRow>
            <TableCell>{product.id}</TableCell>
            <TableCell>{product.name}</TableCell>
            <TableCell align="right">{toCurrency(product.price)}</TableCell>
            <TableCell sx={{ '& > :not(:last-child)': { mr: 1 } }}>
                {product.categories.map(c => <Chip key={c.id} label={c.name} />)}
            </TableCell>
            <TableCell align="right" sx={{ py: 0 }}>
                <Link to={`${product.id}/edit`}>
                    <IconButton aria-label="Edit product">
                        <EditIcon />
                    </IconButton>
                </Link>
            </TableCell>
        </TableRow>
    );
}

export function Products() {
    const products = useLoaderData();

    return (
        <Box>
            <PageHeader>
                <PageHeaderText>Products</PageHeaderText>
            </PageHeader>
            <Box>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>Name</TableCell>
                                <TableCell align="right">Price</TableCell>
                                <TableCell>Categories</TableCell>
                                <TableCell />
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {products.map(p => <ProductRow key={p.id} product={p} />)}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Box>
    );
}