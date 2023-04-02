import { forwardRef, useState } from 'react'
import { useQuery } from "react-query";
import { v4 as uuid } from 'uuid';
import { Autocomplete as MuiAutocomplete, CircularProgress, TextField } from '@mui/material';

export const Autocomplete = forwardRef((props, ref) => {
    const { loadOptions, value, onChange, label, disabled, size, ...fieldProps } = props;

    const [open, setOpen] = useState(false);

    const [queryKey] = useState(uuid);

    const { isLoading, data: options } = useQuery({
        queryKey: queryKey,
        queryFn: loadOptions,
        enabled: open,
    });

    return (
        <MuiAutocomplete
            ref={ref}
            renderInput={params => (
                <TextField
                    {...fieldProps}
                    {...params}
                    label={label}
                    InputProps={{
                        ...params.InputProps,
                        endAdornment: (
                            <>
                                {isLoading ? <CircularProgress color="inherit" size={20} /> : null}
                                {params.InputProps.endAdornment}
                            </>
                        )
                    }}
                />
            )}
            options={options ?? []}
            isOptionEqualToValue={(option, value) => option.id === value.id}
            open={open}
            onOpen={() => setOpen(true)}
            onClose={() => setOpen(false)}
            loading={isLoading}
            value={value}
            onChange={(e, value) => onChange(value)}
            disabled={disabled}
            size={size}
        />
    );
});