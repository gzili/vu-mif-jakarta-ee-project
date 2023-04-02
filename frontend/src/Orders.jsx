import AddIcon from '@mui/icons-material/Add';
import {
    Box,
    Button,
    IconButton,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import { Link, useLoaderData } from 'react-router-dom';
import { api } from "./api.js";
import { PageHeader, PageHeaderActions, PageHeaderText } from "./components/PageHeader.jsx";
import { toCurrency } from "./utils.js";

export function ordersLoader() {
    return api.get('orders').json();
}

function OrderRow(props) {
    const { order } = props;

    return (
        <TableRow>
            <TableCell>#{order.id}</TableCell>
            <TableCell>{order.dateCreated}</TableCell>
            <TableCell align="right">{toCurrency(order.subtotal)}</TableCell>
            <TableCell align="right">{toCurrency(order.discountTotal)}</TableCell>
            <TableCell align="right">{toCurrency(order.total)}</TableCell>
            <TableCell align="center" sx={{ p: 0 }}>
                <Link to={`${order.id}`}>
                    <IconButton aria-label="view order">
                        <ChevronRightIcon />
                    </IconButton>
                </Link>
            </TableCell>
        </TableRow>
    );
}

export function Orders() {
    const orders = useLoaderData();

    return (
        <Box>
            <PageHeader>
                <PageHeaderText>Orders</PageHeaderText>
                <PageHeaderActions>
                    <Button component={Link} to="new" variant="contained" startIcon={<AddIcon />} disableElevation>New Order</Button>
                </PageHeaderActions>
            </PageHeader>
            <Box py={2}>
                <TableContainer component={Paper} variant="outlined">
                    <Table sx={{ '& tbody tr:last-child td': { border: 0 } }}>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>Date Created</TableCell>
                                <TableCell align="right">Subtotal</TableCell>
                                <TableCell align="right">Discount</TableCell>
                                <TableCell align="right">Total</TableCell>
                                <TableCell></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {orders.map(o => <OrderRow key={o.id} order={o} />)}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Box>
    );
}