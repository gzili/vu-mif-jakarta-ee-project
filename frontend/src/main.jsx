import React from 'react'
import ReactDOM from 'react-dom/client'
import { QueryClient, QueryClientProvider } from "react-query";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { CssBaseline } from "@mui/material";
import { NewOrder } from "./NewOrder.jsx";
import { Root } from "./Root.jsx";
import { Orders, ordersLoader } from "./Orders.jsx";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            retry: false,
        },
    },
});

const router = createBrowserRouter([
    {
        path: '/',
        element: <Root />,
        children: [
            {
                path: 'products',
            },
            {
                path: 'products/:id',
            },
            {
                path: 'orders',
                loader: ordersLoader,
                element: <Orders />
            },
            {
                path: 'orders/new',
                element: <NewOrder />,
            },
            {
                path: 'orders/:id',
            },
        ],
    }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <CssBaseline />
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={router}/>
        </QueryClientProvider>
    </React.StrictMode>,
)
