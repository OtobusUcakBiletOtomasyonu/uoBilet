
import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import theme from '../../core/theme/theme';
import { AuthProvider } from '../../features/auth/model/AuthContext';

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: 1,
            refetchOnWindowFocus: false,
        },
    },
});

interface AppProviderProps {
    children: React.ReactNode;
}

export const AppProvider: React.FC<AppProviderProps> = ({ children }) => {
    return (
        <QueryClientProvider client={queryClient}>
            <ThemeProvider theme={theme}>
                <AuthProvider>
                    <CssBaseline />
                    {children}
                </AuthProvider>
            </ThemeProvider>
        </QueryClientProvider>
    );
};
