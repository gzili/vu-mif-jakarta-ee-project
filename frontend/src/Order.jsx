import { Box, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { useLoaderData } from "react-router-dom";
import { api } from "./api.js";
import { PageHeader, PageHeaderText } from "./components/PageHeader.jsx";
import { toCurrency } from "./utils.js";

export function orderLoader({ params }) {
    return api.get(`orders/${params.id}`).json();
}

function ItemRow(props) {
    const { item } = props;

    return (
        <TableRow>
            <TableCell>{item.product.name}</TableCell>
            <TableCell align="right">{toCurrency(item.price)}</TableCell>
            <TableCell align="right">{item.quantity}</TableCell>
            <TableCell align="right">{toCurrency(item.subtotal)}</TableCell>
            <TableCell align="right">{toCurrency(item.discount)}</TableCell>
            <TableCell align="right">{toCurrency(item.total)}</TableCell>
        </TableRow>
    );
}

export function Order() {
    const order = useLoaderData();

    // TODO: dateCreated

    return (
        <Box>
            <PageHeader>
                <PageHeaderText>Order #{order.id}</PageHeaderText>
            </PageHeader>
            <Box py={2}>
                <TableContainer component={Paper} variant="outlined">
                    <Table sx={{ '& tbody tr:last-child td': { border: 0 } }}>
                        <TableHead>
                            <TableRow>
                                <TableCell>Product Name</TableCell>
                                <TableCell align="right">Price</TableCell>
                                <TableCell align="right">Quantity</TableCell>
                                <TableCell align="right">Subtotal</TableCell>
                                <TableCell align="right">Discount</TableCell>
                                <TableCell align="right">Total</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {order.items.map(i => <ItemRow key={i.id} item={i} />)}
                            <TableRow>
                                <TableCell colSpan={4}></TableCell>
                                <TableCell align="right" sx={{ fontWeight: 'bold' }}>Subtotal</TableCell>
                                <TableCell align="right">{toCurrency(order.subtotal)}</TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell colSpan={4}></TableCell>
                                <TableCell align="right" sx={{ fontWeight: 'bold' }}>Discount total</TableCell>
                                <TableCell align="right">{toCurrency(order.discountTotal)}</TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell colSpan={4}></TableCell>
                                <TableCell align="right" sx={{ fontSize: 20, fontWeight: 'bold' }}>Grand total</TableCell>
                                <TableCell align="right" sx={{ fontSize: 20, fontWeight: 'bold' }}>{toCurrency(order.total)}</TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Box>
    );
}