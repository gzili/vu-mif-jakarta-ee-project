import { lightFormat } from "date-fns";

export function toCurrency(number) {
    return new Intl.NumberFormat('lt-LT', { style: 'currency', currency: 'EUR' }).format(number);
}

export function formatIsoDateString(isoDate) {
    return lightFormat(Date.parse(isoDate), 'yyyy-MM-dd HH:mm:ss');
}
