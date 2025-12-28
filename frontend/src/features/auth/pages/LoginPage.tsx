
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { useMutation } from '@tanstack/react-query';
import { Link, useNavigate } from 'react-router-dom';
import { Box, Button, TextField, Typography, Container, Alert, Paper } from '@mui/material';
import { authApi, loginSchema, type LoginArgs } from '../api/authApi';
import { useAuth } from '../model/AuthContext';

const LoginPage = () => {
    const navigate = useNavigate();
    const { login } = useAuth();
    const { register, handleSubmit, formState: { errors } } = useForm<LoginArgs>({
        resolver: zodResolver(loginSchema),
    });

    const mutation = useMutation({
        mutationFn: authApi.login,
        onSuccess: (response) => {
            login(response.data);
            navigate('/');
        },
    });

    const onSubmit = (data: LoginArgs) => {
        mutation.mutate(data);
    };

    return (
        <Container maxWidth="xs">
            <Box sx={{ mt: 8, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Paper elevation={3} sx={{ p: 4, width: '100%', borderRadius: 2 }}>
                    <Typography component="h1" variant="h5" align="center" gutterBottom fontWeight="bold">
                        Giriş Yap
                    </Typography>

                    {mutation.isError && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {(mutation.error as any).response?.data?.message || 'Giriş başarısız. Lütfen bilgilerinizi kontrol edin.'}
                        </Alert>
                    )}

                    <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email Adresi"
                            autoComplete="email"
                            autoFocus
                            {...register('email')}
                            error={!!errors.email}
                            helperText={errors.email?.message}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Şifre"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                            {...register('password')}
                            error={!!errors.password}
                            helperText={errors.password?.message}
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2, py: 1.5, fontSize: '1rem' }}
                            disabled={mutation.isPending}
                        >
                            {mutation.isPending ? 'Giriş Yapılıyor...' : 'Giriş Yap'}
                        </Button>
                        <Box textAlign="center">
                            <Link to="/register" style={{ textDecoration: 'none', color: '#1976d2' }}>
                                Hesabınız yok mu? Kayıt Ol
                            </Link>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
};

export default LoginPage;
