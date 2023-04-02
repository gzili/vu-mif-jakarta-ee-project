import { Box, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import WidgetsIcon from '@mui/icons-material/Widgets';
import ReceiptIcon from '@mui/icons-material/Receipt';
import { NavLink as RouterNavLink, Outlet } from "react-router-dom";

function ListItemLink(props) {
    const { to, icon, text } = props;

    return (
        <ListItem component={RouterNavLink} to={to} disablePadding sx={{ color: 'inherit' }}>
            <ListItemButton>
                <ListItemIcon sx={{ minWidth: 0, pr: 2 }}>{icon}</ListItemIcon>
                <ListItemText primary={text} />
            </ListItemButton>
        </ListItem>
    );
}

export function Root() {
    return (
        <Box
            sx={{
                position: "absolute",
                inset: 0,
                display: "flex",
            }}
        >
            <Drawer
                sx={{
                    width: 240,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        position: 'static',
                        width: '100%',
                    }
                }}
                variant="permanent"
                anchor="left"
            >
                <List>
                    <ListItemLink to="products" icon={<WidgetsIcon />} text="Products" />
                    <ListItemLink to="orders" icon={<ReceiptIcon />} text="Orders" />
                </List>
            </Drawer>
            <Box
                component="main"
                sx={{
                    flex: 1,
                    py: 2,
                    px: 4,
                }}
            >
                <Outlet />
            </Box>
        </Box>
    );
}