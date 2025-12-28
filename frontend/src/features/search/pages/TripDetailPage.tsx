import { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { searchApi } from '../api/searchApi';
import SeatLayout from '../components/SeatLayout';
import './TripDetailPage.css';

interface SelectedSeat {
    number: number;
    gender: 'KADIN' | 'ERKEK';
}

const TripDetailPage = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const seferId = Number(id);

    // State now stores objects with gender
    const [selectedSeats, setSelectedSeats] = useState<SelectedSeat[]>([]);

    const tripQuery = useQuery({
        queryKey: ['seferler'],
        queryFn: searchApi.getAllSeferler,
    });

    const seatsQuery = useQuery({
        queryKey: ['koltuklar', seferId],
        queryFn: () => searchApi.getKoltuklar(seferId),
        enabled: !!seferId,
    });

    const trip = tripQuery.data?.data?.find(s => s.id === seferId);

    const handleSeatSelect = (seatNum: number, gender?: 'KADIN' | 'ERKEK') => {
        // Check if already selected
        const existingIndex = selectedSeats.findIndex(s => s.number === seatNum);

        if (existingIndex !== -1) {
            // Deselect: remove from array
            setSelectedSeats(prev => prev.filter(s => s.number !== seatNum));
        } else {
            // Select: check limit and add
            if (selectedSeats.length >= 5) {
                alert('En fazla 5 koltuk seçebilirsiniz.');
                return;
            }
            if (!gender) return; // Should not happen with new logic, but safety check

            setSelectedSeats(prev => [...prev, { number: seatNum, gender }]);
        }
    };

    const handlePayment = () => {
        // Navigate to payment with state
        navigate('/payment', { state: { trip, selectedSeats } });
    };

    if (tripQuery.isLoading || seatsQuery.isLoading) {
        return <div className="text-center py-lg">Yükleniyor...</div>;
    }

    if (!trip) {
        return <div className="text-center py-lg text-error">Sefer bulunamadı.</div>;
    }

    // Helper for summary display
    const seatNumbers = selectedSeats.map(s => s.number).sort((a, b) => a - b).join(', ');
    const totalPrice = selectedSeats.length * trip.fiyat;

    return (
        <div className="trip-detail-page">
            <div className="container">
                <div className="detail-layout">

                    {/* Seat Selection Panel */}
                    <div className="seat-selection-card">
                        <h2 className="text-xl font-bold mb-md">Koltuk Seçimi</h2>
                        <div className="mb-lg">
                            <span className="font-semibold text-primary">{trip.kalkisYeri} - {trip.varisYeri}</span>
                            <span className="text-secondary mx-2">|</span>
                            <span className="text-secondary">
                                {new Date(trip.kalkisZamani).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                            </span>
                        </div>

                        <div className="flex justify-center mt-lg">
                            {seatsQuery.data?.data ? (
                                <SeatLayout
                                    koltuklar={seatsQuery.data.data}
                                    selectedSeats={selectedSeats.map(s => s.number)}
                                    onSeatSelect={handleSeatSelect}
                                    koltukDuzeni={trip.koltukDuzeni}
                                />
                            ) : (
                                <div className="text-error">Koltuk bilgisi yüklenemedi.</div>
                            )}
                        </div>
                    </div>

                    {/* Summary Sidebar */}
                    <div className="summary-card">
                        <h3 className="text-lg font-bold mb-md border-b pb-sm">Özet</h3>

                        <div className="mb-md">
                            <div className="text-xs text-secondary mb-xs">Sefer</div>
                            <div className="font-semibold">{trip.firmaAdi}</div>
                            <div className="text-sm">{new Date(trip.kalkisZamani).toLocaleString()}</div>
                        </div>

                        <div className="mb-md">
                            <div className="text-xs text-secondary mb-xs">Seçilen Koltuklar</div>
                            <div className="flex flex-wrap gap-sm">
                                {selectedSeats.length > 0 ? (
                                    selectedSeats.map(s => (
                                        <div key={s.number} className="text-sm bg-gray-100 px-2 py-1 rounded">
                                            {s.number} <span style={{ fontSize: '0.7em', color: '#666' }}>({s.gender === 'KADIN' ? 'K' : 'E'})</span>
                                        </div>
                                    ))
                                ) : (
                                    <span className="text-secondary">-</span>
                                )}
                            </div>
                        </div>

                        <div className="mb-lg">
                            <div className="text-xs text-secondary mb-xs">Toplam Tutar</div>
                            <div className="text-2xl font-bold text-primary">{totalPrice} ₺</div>
                        </div>

                        <button
                            className="btn btn-primary w-full"
                            style={{ width: '100%' }}
                            disabled={selectedSeats.length === 0}
                            onClick={handlePayment}
                        >
                            Ödemeye Geç
                        </button>
                    </div>

                </div>
            </div>
        </div>
    );
};

export default TripDetailPage;
