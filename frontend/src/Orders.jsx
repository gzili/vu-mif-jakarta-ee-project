import AddIcon from '@mui/icons-material/Add';
import { Box, Button } from "@mui/material";
import { Link } from 'react-router-dom';
import { api } from "./api.js";
import { PageHeader, PageHeaderActions, PageHeaderText } from "./components/PageHeader.jsx";

function ordersLoader() {
    return api.post('orders').json();
}

export function Orders() {
    return (
        <Box>
            <PageHeader>
                <PageHeaderText>Orders</PageHeaderText>
                <PageHeaderActions>
                    <Button component={Link} to="new" variant="contained" startIcon={<AddIcon />} disableElevation>New Order</Button>
                </PageHeaderActions>
            </PageHeader>
        </Box>
    );
}