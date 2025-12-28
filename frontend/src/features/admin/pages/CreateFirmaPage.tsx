
import { useState } from 'react';
import { Box, Container, Paper, Typography, TextField, Button, Alert, Stack } from '@mui/material';
import { adminApi } from '../api/adminApi';

const CreateFirmaPage = () => {
    const [ad, setAd] = useState('');
    const [msg, setMsg] = useState({ type: '', content: '' });

    const handleSubmit = async () => {
        try {
            await adminApi.createFirma(ad);
            setMsg({ type: 'success', content: 'Firma başarıyla oluşturuldu.' });
            setAd('');
        } catch (err) {
            setMsg({ type: 'error', content: 'Firma oluşturulamadı.' });
        }
    };

    return (
        <Box sx={{ minHeight: '100vh', py: 4, bgcolor: '#f5f5f5' }}>
            <Container maxWidth="sm">
                <Paper sx={{ p: 4, borderRadius: 3 }}>
                    <Typography variant="h5" gutterBottom fontWeight="bold">Firma Ekle</Typography>

                    {msg.content && <Alert severity={msg.type as any} sx={{ mb: 2 }}>{msg.content}</Alert>}

                    <Stack spacing={2}>
                        <TextField
                            label="Firma Adı"
                            fullWidth
                            value={ad}
                            onChange={(e) => setAd(e.target.value)}
                        />
                        <Button variant="contained" size="large" onClick={handleSubmit} disabled={!ad}>
                            Oluştur
                        </Button>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    );
};

export default CreateFirmaPage;
