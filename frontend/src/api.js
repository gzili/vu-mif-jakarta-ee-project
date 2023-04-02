import ky from "ky";

const apiUrl = import.meta.env.VITE_API_URL;

if (!apiUrl) {
    throw new Error('VITE_API_URL not set');
}

export const api = ky.create({
    prefixUrl: apiUrl,
    retry: 0,
});