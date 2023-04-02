export function toCurrency(number) {
    return new Intl.NumberFormat('lt-LT', { style: 'currency', currency: 'EUR' }).format(number);
}