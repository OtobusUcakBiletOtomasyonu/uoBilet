
import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { Box, Container, Paper, Typography, TextField, Button, Alert, Stack, MenuItem, FormControl, InputLabel, Select } from '@mui/material';
import { adminApi, type CreateSeferRequest } from '../api/adminApi';

const CITIES = ['Istanbul', 'Ankara', 'Izmir', 'Antalya', 'Bursa', 'Adana', 'Konya', 'Eskisehir'];

const CreateSeferPage = () => {
    const [formData, setFormData] = useState<CreateSeferRequest>({
        kalkisYeri: '',
        varisYeri: '',
        kalkisZamani: '',
        fiyat: 0,
        tip: 'OTOBUS',
        koltukDuzeni: '2+1',
        firmaId: undefined
    });

    const [msg, setMsg] = useState({ type: '', content: '' });

    const { data: firms } = useQuery({
        queryKey: ['firmalar'],
        queryFn: adminApi.getAllFirmalar
    });

    const handleChange = (field: keyof CreateSeferRequest, value: any) => {
        setFormData(prev => ({ ...prev, [field]: value }));
    };

    const handleSubmit = async () => {
        try {
            await adminApi.createSefer(formData);
            setMsg({ type: 'success', content: 'Sefer başarıyla oluşturuldu.' });
        } catch (err) {
            setMsg({ type: 'error', content: 'Sefer oluşturulamadı.' });
            console.error(err);
        }
    };

    return (
        <Box sx={{ minHeight: '100vh', py: 4, bgcolor: '#f5f5f5' }}>
            <Container maxWidth="md">
                <Paper sx={{ p: 4, borderRadius: 3 }}>
                    <Typography variant="h5" gutterBottom fontWeight="bold">Sefer Ekle</Typography>

                    {msg.content && <Alert severity={msg.type as any} sx={{ mb: 2 }}>{msg.content}</Alert>}

                    <Stack spacing={2}>
                        <FormControl fullWidth>
                            <InputLabel>Firma Seçin</InputLabel>
                            <Select
                                value={formData.firmaId || ''}
                                label="Firma Seçin"
                                onChange={(e: any) => handleChange('firmaId', e.target.value)}
                            >
                                {firms?.data?.map(f => (
                                    <MenuItem key={f.id} value={f.id}>{f.ad}</MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <Stack direction="row" spacing={2}>
                            <FormControl fullWidth>
                                <InputLabel>Nereden</InputLabel>
                                <Select
                                    value={formData.kalkisYeri}
                                    label="Nereden"
                                    onChange={(e: any) => handleChange('kalkisYeri', e.target.value)}
                                >
                                    {CITIES.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                                </Select>
                            </FormControl>
                            <FormControl fullWidth>
                                <InputLabel>Nereye</InputLabel>
                                <Select
                                    value={formData.varisYeri}
                                    label="Nereye"
                                    onChange={(e: any) => handleChange('varisYeri', e.target.value)}
                                >
                                    {CITIES.map(c => <MenuItem key={c} value={c}>{c}</MenuItem>)}
                                </Select>
                            </FormControl>
                        </Stack>

                        <TextField
                            type="datetime-local"
                            label="Kalkış Zamanı"
                            InputLabelProps={{ shrink: true }}
                            fullWidth
                            value={formData.kalkisZamani}
                            onChange={(e) => handleChange('kalkisZamani', e.target.value)}
                        />

                        <Stack direction="row" spacing={2}>
                            <TextField
                                label="Fiyat"
                                type="number"
                                fullWidth
                                value={formData.fiyat}
                                onChange={(e) => handleChange('fiyat', Number(e.target.value))}
                            />
                            <FormControl fullWidth>
                                <InputLabel>Tip</InputLabel>
                                <Select
                                    value={formData.tip}
                                    label="Tip"
                                    onChange={(e: any) => handleChange('tip', e.target.value)}
                                >
                                    <MenuItem value="OTOBUS">Otobüs</MenuItem>
                                    <MenuItem value="UCAK">Uçak</MenuItem>
                                </Select>
                            </FormControl>
                            {formData.tip === 'OTOBUS' && (
                                <FormControl fullWidth>
                                    <InputLabel>Koltuk Düzeni</InputLabel>
                                    <Select
                                        value={formData.koltukDuzeni}
                                        label="Koltuk Düzeni"
                                        onChange={(e: any) => handleChange('koltukDuzeni', e.target.value)}
                                    >
                                        <MenuItem value="2+1">2+1</MenuItem>
                                        <MenuItem value="2+2">2+2</MenuItem>
                                    </Select>
                                </FormControl>
                            )}
                        </Stack>

                        <Button variant="contained" size="large" onClick={handleSubmit} sx={{ mt: 2 }}>
                            Oluştur
                        </Button>
                    </Stack>
                </Paper>
            </Container>
        </Box>
    );
};

export default CreateSeferPage;
