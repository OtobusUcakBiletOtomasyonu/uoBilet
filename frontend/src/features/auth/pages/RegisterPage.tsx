
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { useMutation } from '@tanstack/react-query';
import { Link, useNavigate } from 'react-router-dom';
import { Box, Button, TextField, Typography, Container, Alert, Paper } from '@mui/material';
import { authApi, registerSchema, type RegisterArgs } from '../api/authApi';

const RegisterPage = () => {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm<RegisterArgs>({
        resolver: zodResolver(registerSchema),
    });

    const mutation = useMutation({
        mutationFn: authApi.register,
        onSuccess: () => {
            // Auto login or redirect to login
            navigate('/login', { state: { message: 'Kayıt başarılı! Lütfen giriş yapın.' } });
        },
    });

    const onSubmit = (data: RegisterArgs) => {
        mutation.mutate(data);
    };

    return (
        <Container maxWidth="xs" sx={{ pb: 5 }}>
            <Box sx={{ mt: 4, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Paper elevation={3} sx={{ p: 4, width: '100%', borderRadius: 2 }}>
                    <Typography component="h1" variant="h5" align="center" gutterBottom fontWeight="bold">
                        Kayıt Ol
                    </Typography>

                    {mutation.isError && (
                        <Alert severity="error" sx={{ mb: 2 }}>
                            {(mutation.error as any).response?.data?.message || 'Kayıt işlemi başarısız.'}
                        </Alert>
                    )}

                    <Box component="form" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Ad"
                            {...register('ad')}
                            error={!!errors.ad}
                            helperText={errors.ad?.message}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Soyad"
                            {...register('soyad')}
                            error={!!errors.soyad}
                            helperText={errors.soyad?.message}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Email"
                            type="email"
                            {...register('email')}
                            error={!!errors.email}
                            helperText={errors.email?.message}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Telefon"
                            placeholder="5XX..."
                            {...register('telefon')}
                            error={!!errors.telefon}
                            helperText={errors.telefon?.message}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            label="Şifre"
                            type="password"
                            {...register('password')}
                            error={!!errors.password}
                            helperText={errors.password?.message}
                        />

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2, py: 1.5 }}
                            disabled={mutation.isPending}
                        >
                            {mutation.isPending ? 'Kaydediliyor...' : 'Kayıt Ol'}
                        </Button>
                        <Box textAlign="center">
                            <Link to="/login" style={{ textDecoration: 'none', color: '#1976d2' }}>
                                Zaten hesabınız var mı? Giriş Yap
                            </Link>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
};

export default RegisterPage;
