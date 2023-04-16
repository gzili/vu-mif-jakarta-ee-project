import { Box, Button, CircularProgress } from "@mui/material";
import { grey } from '@mui/material/colors'
import { useMutation, useQuery } from "react-query";
import { api } from "./api.js";
import { PageHeader, PageHeaderText } from "./components/PageHeader.jsx";

function getUpdateMessage(count) {
    switch (count) {
        case 0:
            return 'No updates found.';
        case 1:
            return '1 update found';
        default:
            return `${count} updates found`;
    }
}

function UpdateStatus(props) {
    const { data } = props;

    if (data.checking) {
        return (
            <Box display="flex" alignItems="center">
                <CircularProgress size={20} />
                <Box ml={1}>Checking for updates</Box>
            </Box>
        );
    }

    if (data.updateCount !== undefined) {
        return (
            <Box>
                <Box fontWeight="bold" fontSize="20px">{getUpdateMessage(data.updateCount)}</Box>
                <Box color={grey[600]}>Last check: {data.lastCheckDate}</Box>
            </Box>
        );
    }

    return null;
}

export function Updates() {
    const { data, refetch } = useQuery({
        queryKey: 'updates',
        queryFn: () => api.get('updates').json(),
        refetchInterval: data => data?.checking ? 1000 : undefined,
    });

    const { mutate: checkForUpdates } = useMutation({
        mutationFn: () => api.post('updates'),
        onSuccess: () => refetch(),
    });

    return (
        <Box>
            <PageHeader>
                <PageHeaderText>Updates</PageHeaderText>
            </PageHeader>
            <Box py={2}>
                {data && (
                    <>
                        <UpdateStatus data={data} />
                        {!data.checking && (
                            <Box mt={2}>
                                <Button variant="contained" onClick={() => checkForUpdates()}>Check for updates</Button>
                            </Box>
                        )}
                    </>
                )}
            </Box>
        </Box>
    );
}