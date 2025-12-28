import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';
import { useAuth } from '../../auth/model/AuthContext';
import { paymentApi } from '../api/paymentApi';
import type { SeferDTO } from '../../search/model/types';
import { searchApi } from '../../search/api/searchApi';
import './PaymentPage.css';

// Updated interface to match TripDetailPage state
interface SelectedSeat {
    number: number;
    gender: 'KADIN' | 'ERKEK';
}

interface LocationState {
    trip: SeferDTO;
    selectedSeats: SelectedSeat[];
}

const PaymentPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { user } = useAuth();
    const queryClient = useQueryClient();

    const state = location.state as LocationState;
    const { trip, selectedSeats } = state || {};

    const [cardName, setCardName] = useState('');
    const [cardNumber, setCardNumber] = useState('');
    const [expiry, setExpiry] = useState('');
    const [cvc, setCvc] = useState('');
    const [successMsg, setSuccessMsg] = useState('');
    const [errorMsg, setErrorMsg] = useState('');
    const [isProcessing, setIsProcessing] = useState(false);

    const handlePayment = async () => {
        setErrorMsg('');
        setSuccessMsg('');
        setIsProcessing(true);

        if (!trip || !selectedSeats || selectedSeats.length === 0) {
            setErrorMsg('Geçersiz işlem.');
            setIsProcessing(false);
            return;
        }

        if (!user) {
            setErrorMsg('Lütfen giriş yapınız.');
            setIsProcessing(false);
            return;
        }

        try {
            // 1. Get Seat IDs from backend to ensure they exist
            // Note: In a real app we might rely on ids passed from previous screen if available, 
            // but fetching ensures freshness and we need mapping from Number -> ID
            const seatResponse = await searchApi.getKoltuklar(trip.id);
            const allSeats = seatResponse.data;

            // Map selected seat numbers to actual Seat Objects
            const seatNumbers = selectedSeats.map(s => s.number);
            const targetSeats = allSeats.filter(s => seatNumbers.includes(Number(s.numara)));

            if (targetSeats.length !== selectedSeats.length) {
                throw new Error('Bazı koltuklar bulunamadı veya dolmuş olabilir.');
            }

            // 2. Loop Purchase (Sequential)
            // Backend takes 1 seat per request.
            for (const seat of targetSeats) {
                await paymentApi.satinAl({
                    yolcuId: user.id,
                    koltukId: seat.id,
                    odemeStrategy: 'KREDI_KARTI',
                    tutar: trip.fiyat
                });
            }

            setSuccessMsg('Ödeme Başarılı! Biletleriniz oluşturuldu.');
            queryClient.invalidateQueries({ queryKey: ['koltuklar'] });
            queryClient.invalidateQueries({ queryKey: ['myTickets'] });

            // Redirect after delay
            setTimeout(() => {
                navigate('/my-tickets');
            }, 2000);

        } catch (err: any) {
            console.error(err);
            setErrorMsg(err.response?.data?.message || err.message || 'Ödeme sırasında hata oluştu.');
            setIsProcessing(false);
        }
    };

    if (!trip || !selectedSeats) {
        return (
            <div className="payment-page">
                <div className="container" style={{ textAlign: 'center' }}>
                    <div className="alert alert-error">Eksik bilgi. Lütfen sefer seçimini tekrarlayın.</div>
                    <button className="btn btn-primary mt-lg" onClick={() => navigate('/')}>Ana Sayfaya Dön</button>
                </div>
            </div>
        );
    }

    return (
        <div className="payment-page">
            <div className="container">
                <div className="payment-layout">
                    <h1 className="text-2xl font-bold text-center mb-lg">Güvenli Ödeme</h1>

                    {/* Trip Summary Card */}
                    <div className="payment-card">
                        <h2 className="section-title">Sefer Bilgileri</h2>
                        <div className="trip-summary">
                            <div className="trip-route">{trip.firmaAdi}</div>
                            <div className="text-lg">{trip.kalkisYeri} ➝ {trip.varisYeri}</div>
                            <div className="trip-time">{new Date(trip.kalkisZamani).toLocaleString()}</div>
                        </div>

                        <div className="seats-info">
                            <span className="text-secondary font-medium">Koltuklar</span>
                            <div className="seat-tags">
                                {selectedSeats.map(s => (
                                    <span key={s.number} className="seat-tag">
                                        {s.number} ({s.gender === 'KADIN' ? 'K' : 'E'})
                                    </span>
                                ))}
                            </div>
                        </div>

                        <div className="total-price">
                            {selectedSeats.length * trip.fiyat} ₺
                        </div>
                    </div>

                    {/* Payment Form Card */}
                    <div className="payment-card">
                        <h2 className="section-title">Kart Bilgileri</h2>

                        {errorMsg && <div className="alert alert-error mb-md">{errorMsg}</div>}
                        {successMsg && <div className="alert alert-success mb-md">{successMsg}</div>}

                        <div className="form-group">
                            <label className="form-label">Kart Üzerindeki İsim</label>
                            <input
                                type="text"
                                className="form-input"
                                placeholder="Ad Soyad"
                                value={cardName}
                                onChange={(e) => setCardName(e.target.value)}
                            />
                        </div>

                        <div className="form-group">
                            <label className="form-label">Kart Numarası</label>
                            <input
                                type="text"
                                className="form-input"
                                placeholder="0000 0000 0000 0000"
                                value={cardNumber}
                                onChange={(e) => setCardNumber(e.target.value)}
                            />
                        </div>

                        <div className="form-row">
                            <div className="form-group" style={{ flex: 1 }}>
                                <label className="form-label">Ay/Yıl</label>
                                <input
                                    type="text"
                                    className="form-input"
                                    placeholder="MM/YY"
                                    value={expiry}
                                    onChange={(e) => setExpiry(e.target.value)}
                                />
                            </div>
                            <div className="form-group" style={{ flex: 1 }}>
                                <label className="form-label">CVC</label>
                                <input
                                    type="text"
                                    className="form-input"
                                    placeholder="123"
                                    value={cvc}
                                    onChange={(e) => setCvc(e.target.value)}
                                />
                            </div>
                        </div>

                        <button
                            className="btn btn-primary payment-btn"
                            disabled={isProcessing || !!successMsg}
                            onClick={handlePayment}
                        >
                            {isProcessing ? 'İşleniyor...' : 'Ödemeyi Tamamla'}
                        </button>

                        <div className="secure-badge">
                            <span style={{ fontSize: '1.2em' }}>🔒</span>
                            256-bit SSL ile güvenli ödeme
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PaymentPage;
