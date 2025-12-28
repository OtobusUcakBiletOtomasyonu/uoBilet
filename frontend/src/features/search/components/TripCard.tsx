import React from 'react';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import FlightIcon from '@mui/icons-material/Flight';
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';
import type { SeferDTO } from '../model/types';
import { format } from 'date-fns';

interface TripCardProps {
    sefer: SeferDTO;
    onSelect: (sefer: SeferDTO) => void;
}

const TripCard: React.FC<TripCardProps> = ({ sefer, onSelect }) => {
    const isBus = sefer.tip === 'OTOBUS';

    return (
        <div className="trip-card">
            <div className="trip-content">

                {/* Firm Info */}
                <div className="trip-info">
                    <div className="flex items-center gap-sm">
                        {isBus ? <DirectionsBusIcon color="primary" /> : <FlightIcon color="secondary" />}
                        <span className="firm-name">{sefer.firmaAdi}</span>
                    </div>

                    <div className="trip-time">
                        <div className="time-val">{format(new Date(sefer.kalkisZamani), 'HH:mm')}</div>
                        <div className="time-label">Kalkış</div>
                    </div>

                    <div className="trip-route">
                        <ArrowRightAltIcon />
                        <span className="time-label">Süre: ~6 Saat</span>
                    </div>
                </div>

                {/* Route Info */}
                <div className="flex flex-col items-center">
                    <span className="text-secondary" style={{ fontSize: '0.9rem' }}>
                        {sefer.kalkisYeri} - {sefer.varisYeri}
                    </span>
                    {sefer.koltukDuzeni && (
                        <span className="text-secondary" style={{ fontSize: '0.8rem', background: '#f5f5f5', padding: '2px 8px', borderRadius: '4px', marginTop: '4px' }}>
                            {sefer.koltukDuzeni}
                        </span>
                    )}
                </div>

                {/* Price & Action */}
                <div className="trip-action">
                    <div className="trip-price">
                        {sefer.fiyat} <span className="currency">TL</span>
                    </div>
                    <button className="select-btn" onClick={() => onSelect(sefer)}>
                        Koltuk Seç
                    </button>
                </div>
            </div>
        </div>
    );
};

export default TripCard;
