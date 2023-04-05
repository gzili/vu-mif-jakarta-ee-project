import { api } from "./api.js";

export function productLoader({ params }) {
    return api.get(`products/${params.id}`).json();
}