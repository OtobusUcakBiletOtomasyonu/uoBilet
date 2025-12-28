
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        mode: 'light',
        primary: {
            main: '#2563EB', // Modern Blue
            light: '#60A5FA',
            dark: '#1E40AF',
            contrastText: '#ffffff',
        },
        secondary: {
            main: '#F59E0B', // Amber for actions/highlights
            light: '#FBBF24',
            dark: '#D97706',
            contrastText: '#ffffff',
        },
        background: {
            default: '#F3F4F6',
            paper: '#FFFFFF',
        },
        text: {
            primary: '#1F2937',
            secondary: '#6B7280',
        },
    },
    typography: {
        fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
        h1: { fontSize: '2.5rem', fontWeight: 700 },
        h2: { fontSize: '2rem', fontWeight: 600 },
        h3: { fontSize: '1.75rem', fontWeight: 600 },
        h4: { fontSize: '1.5rem', fontWeight: 600 },
        h5: { fontSize: '1.25rem', fontWeight: 500 },
        h6: { fontSize: '1rem', fontWeight: 500 },
        button: { textTransform: 'none', fontWeight: 600 },
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    borderRadius: 8,
                    padding: '8px 16px',
                },
                contained: {
                    boxShadow: 'none',
                    '&:hover': {
                        boxShadow: '0px 2px 4px rgba(0,0,0,0.1)',
                    },
                },
            },
        },
        MuiCard: {
            styleOverrides: {
                root: {
                    borderRadius: 12,
                    boxShadow: '0px 1px 3px rgba(0,0,0,0.1)',
                },
            },
        },
        MuiTextField: {
            defaultProps: {
                variant: 'outlined',
                size: 'small',
            },
        },
    },
});

export default theme;
