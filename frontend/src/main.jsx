import React from 'react'
import ReactDOM from 'react-dom/client'
import { QueryClient, QueryClientProvider } from "react-query";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { CssBaseline } from "@mui/material";
import { NewOrder } from "./NewOrder.jsx";
import { Order, orderLoader } from "./Order.jsx";
import { productLoader } from "./Product.jsx";
import { editProductAction, ProductEdit } from "./ProductEdit.jsx";
import { Products, productsLoader } from "./Products.jsx";
import { Root } from "./Root.jsx";
import { Orders, ordersLoader } from "./Orders.jsx";
import { Updates } from "./Updates.jsx";

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
                loader: productsLoader,
                element: <Products />,
            },
            {
                path: 'products/:id/edit',
                loader: productLoader,
                action: editProductAction,
                element: <ProductEdit />,
                shouldRevalidate: ({ currentUrl }) => !currentUrl.pathname.includes('edit'),
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
                loader: orderLoader,
                element: <Order />,
            },
            {
                path: 'updates',
                element: <Updates />,
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
