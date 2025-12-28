import React, { useState } from 'react';
import type { KoltukDTO } from '../model/types';
import FemaleIcon from '@mui/icons-material/Female';
import MaleIcon from '@mui/icons-material/Male';

interface SeatLayoutProps {
    koltuklar: KoltukDTO[];
    selectedSeats: number[];
    onSeatSelect: (seatNum: number, gender?: 'KADIN' | 'ERKEK') => void;
    koltukDuzeni?: string;
}

const SeatLayout: React.FC<SeatLayoutProps> = ({ koltuklar, selectedSeats, onSeatSelect, koltukDuzeni = "2+2" }) => {
    const is2plus1 = koltukDuzeni === "2+1";
    const [popupSeat, setPopupSeat] = useState<number | null>(null);

    // Sort by number
    const sortedSeats = [...koltuklar].sort((a, b) => Number(a.numara) - Number(b.numara));

    // Chunking based on layout
    // 2+1: 3 seats per row
    // 2+2: 4 seats per row
    const seatsPerRow = is2plus1 ? 3 : 4;
    const rows = [];
    for (let i = 0; i < sortedSeats.length; i += seatsPerRow) {
        rows.push(sortedSeats.slice(i, i + seatsPerRow));
    }

    const handleSeatClick = (seat: KoltukDTO) => {
        if (seat.durum !== 'BOS') return;
        if (selectedSeats.includes(Number(seat.numara))) {
            // Deselect
            onSeatSelect(Number(seat.numara));
            setPopupSeat(null);
        } else {
            // Open gender popup
            setPopupSeat(Number(seat.numara));
        }
    };

    const handleGenderSelect = (gender: 'KADIN' | 'ERKEK') => {
        if (popupSeat) {
            onSeatSelect(popupSeat, gender);
            setPopupSeat(null);
        }
    };

    const renderSeat = (seat?: KoltukDTO) => {
        if (!seat) return <div className="seat-item aisle" />;

        const isSelected = selectedSeats.includes(Number(seat.numara));
        const isOccupied = seat.durum === 'DOLU' || seat.durum === 'REZERVE';

        // Gender logic for occupied seats (assuming backend sends it in 'cinsiyet' field not yet in type but in DTO)
        // casting to any for now as type might not update immediately in frontend
        const gender = (seat as any).cinsiyet;
        const genderClass = gender === 'KADIN' ? 'woman' : gender === 'ERKEK' ? 'man' : '';

        return (
            <div
                key={seat.numara}
                className={`seat-item ${isSelected ? 'selected' : ''} ${isOccupied ? 'occupied' : ''} ${genderClass}`}
                onClick={() => handleSeatClick(seat)}
            >
                {seat.numara}

                {/* Gender Popup */}
                {popupSeat === Number(seat.numara) && (
                    <div className="gender-popup">
                        <button className="gender-btn woman" onClick={(e) => { e.stopPropagation(); handleGenderSelect('KADIN'); }}>
                            <FemaleIcon fontSize="small" />
                        </button>
                        <button className="gender-btn man" onClick={(e) => { e.stopPropagation(); handleGenderSelect('ERKEK'); }}>
                            <MaleIcon fontSize="small" />
                        </button>
                    </div>
                )}
            </div>
        );
    };

    return (
        <div className="bus-container">
            <div className="driver-area">
                <div style={{ width: 40, height: 40, border: '2px solid #ccc', borderRadius: '50%', display: 'flex', alignItems: 'center', justifyContent: 'center', marginLeft: 'auto' }}>
                    Direksiyon
                </div>
            </div>

            <div className={`seat-grid ${is2plus1 ? 'layout-2-1' : ''}`}>
                {rows.map((row, rIdx) => (
                    <React.Fragment key={rIdx}>
                        {is2plus1 ? (
                            <>
                                {renderSeat(row[0])}
                                <div className="seat-item aisle" />
                                {renderSeat(row[1])}
                                {renderSeat(row[2])}
                            </>
                        ) : (
                            <>
                                {renderSeat(row[0])}
                                {renderSeat(row[1])}
                                <div className="seat-item aisle" />
                                {renderSeat(row[2])}
                                {renderSeat(row[3])}
                            </>
                        )}
                    </React.Fragment>
                ))}
            </div>

            <div className="seat-legend">
                <div className="legend-item"><div className="legend-box empty"></div> Boş</div>
                <div className="legend-item"><div className="legend-box selected"></div> Seçili</div>
                <div className="legend-item"><div className="legend-box woman"></div> Kadın</div>
                <div className="legend-item"><div className="legend-box man"></div> Erkek</div>
            </div>
        </div>
    );
};

export default SeatLayout;
