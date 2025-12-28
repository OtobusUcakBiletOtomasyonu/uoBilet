
import { Box, Container, Typography, Paper, Stack, Avatar } from '@mui/material';
import { useAuth } from '../auth/model/AuthContext';
import PersonIcon from '@mui/icons-material/Person';

const ProfilePage = () => {
    const { user } = useAuth();

    if (!user) {
        return <Box sx={{ p: 4 }}>Giriş yapmalısınız.</Box>;
    }

    return (
        <Box sx={{ minHeight: '100vh', py: 4, bgcolor: '#f5f5f5' }}>
            <Container maxWidth="sm">
                <Paper sx={{ p: 4, borderRadius: 4, textAlign: 'center' }}>
                    <Avatar sx={{ width: 80, height: 80, mx: 'auto', mb: 2, bgcolor: 'primary.main' }}>
                        <PersonIcon fontSize="large" />
                    </Avatar>
                    <Typography variant="h5" fontWeight="bold" gutterBottom>
                        {user.ad} {user.soyad}
                    </Typography>
                    <Chip label={user.role} color="secondary" size="small" sx={{ mb: 4 }} />

                    <Stack spacing={2} sx={{ textAlign: 'left' }}>
                        <Box p={2} bgcolor="#f0f0f0" borderRadius={2}>
                            <Typography variant="caption" color="text.secondary">Email</Typography>
                            <Typography variant="body1">{user.email}</Typography>
                        </Box>
                        <Box p={2} bgcolor="#f0f0f0" borderRadius={2}>
                            <Typography variant="caption" color="text.secondary">Telefon</Typography>
                            <Typography variant="body1">{user.telefon || '-'}</Typography>
                        </Box>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    );
};

// Start Check: imports Chip?
import { Chip } from '@mui/material';

export default ProfilePage;
