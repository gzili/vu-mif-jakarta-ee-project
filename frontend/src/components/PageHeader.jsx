import { Box, Typography } from "@mui/material";

export function PageHeader(props) {
    const { children } = props;

    return (
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            {children}
        </Box>
    );
}

export function PageHeaderText(props) {
    const { children } = props;

    return (
        <Typography component="h1" sx={{ fontSize: 40, fontWeight: 'bold' }}>
            {children}
        </Typography>
    );
}

export function PageHeaderActions(props) {
    const { children } = props;

    return (
        <Box>
            {children}
        </Box>
    );
}