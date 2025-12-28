import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../auth/model/AuthContext';
import DirectionsBusIcon from '@mui/icons-material/DirectionsBus';
import FlightIcon from '@mui/icons-material/Flight';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import SecureIcon from '@mui/icons-material/VerifiedUser';
import SupportIcon from '@mui/icons-material/HeadsetMic';
import RefundIcon from '@mui/icons-material/CurrencyExchange';
import './HomePage.css';

const HomePage = () => {
    const navigate = useNavigate();
    const { user } = useAuth();
    const [activeTab, setActiveTab] = useState<'bus' | 'plane'>('bus');

    const [kalkis, setKalkis] = useState('Istanbul');
    const [varis, setVaris] = useState('Ankara');
    const [tarih, setTarih] = useState(new Date().toISOString().split('T')[0]);

    const handleSearch = () => {
        if (kalkis && varis && tarih) {
            navigate(`/search?kalkis=${kalkis}&varis=${varis}&tarih=${tarih}&type=${activeTab}`);
        }
    };

    return (
        <div className="home-page">
            <section className="hero-section">
                <div className="container">
                    <h1 className="hero-title">Türkiye'nin Seyahat Uygulaması</h1>
                    <p className="hero-subtitle">
                        {user ? `Hoşgeldin ${user.ad}, ` : ''}Otobüs ve uçak biletini kolayca al.
                    </p>
                </div>
            </section>

            <div className="search-widget-container">
                <div className="search-widget">
                    <div className="widget-tabs">
                        <div
                            className={`widget-tab ${activeTab === 'bus' ? 'active' : ''}`}
                            onClick={() => setActiveTab('bus')}
                        >
                            <DirectionsBusIcon /> Otobüs Bileti
                        </div>
                        <div
                            className={`widget-tab ${activeTab === 'plane' ? 'active' : ''}`}
                            onClick={() => setActiveTab('plane')}
                        >
                            <FlightIcon /> Uçak Bileti
                        </div>
                    </div>

                    <div className="widget-content">
                        <div className="search-form">
                            <div className="form-row">
                                <div className="input-group">
                                    <label className="input-label">Nereden</label>
                                    <input
                                        type="text"
                                        className="input-field"
                                        value={kalkis}
                                        onChange={(e) => setKalkis(e.target.value)}
                                        placeholder="İl veya ilçe yazın"
                                    />
                                    <LocationOnIcon className="input-icon" fontSize="small" />
                                </div>

                                <div className="input-group">
                                    <label className="input-label">Nereye</label>
                                    <input
                                        type="text"
                                        className="input-field"
                                        value={varis}
                                        onChange={(e) => setVaris(e.target.value)}
                                        placeholder="İl veya ilçe yazın"
                                    />
                                    <LocationOnIcon className="input-icon" fontSize="small" />
                                </div>

                                <div className="input-group">
                                    <label className="input-label">Tarih</label>
                                    <input
                                        type="date"
                                        className="input-field"
                                        value={tarih}
                                        onChange={(e) => setTarih(e.target.value)}
                                    />
                                </div>
                            </div>

                            <button className="search-btn" onClick={handleSearch}>
                                {activeTab === 'bus' ? 'Otobüs Bileti Bul' : 'Uçak Bileti Bul'}
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <section className="features-section">
                <div className="container">
                    <div className="text-center mb-md">
                        <h2 style={{ fontSize: '1.75rem', marginBottom: '8px' }}>Neden Ticket App?</h2>
                    </div>

                    <div className="features-grid">
                        <div className="feature-card">
                            <div className="feature-icon">
                                <SecureIcon fontSize="large" />
                            </div>
                            <h3>Güvenli Ödeme</h3>
                            <p className="text-secondary">
                                Tüm ödemeleriniz 3D Secure ile güvence altındadır.
                            </p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">
                                <RefundIcon fontSize="large" />
                            </div>
                            <h3>İptal ve İade</h3>
                            <p className="text-secondary">
                                Koşulsuz iade hakkı ile biletini dilediğin zaman iptal et.
                            </p>
                        </div>
                        <div className="feature-card">
                            <div className="feature-icon">
                                <SupportIcon fontSize="large" />
                            </div>
                            <h3>7/24 Destek</h3>
                            <p className="text-secondary">
                                Müşteri hizmetlerimiz her an yanınızda.
                            </p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default HomePage;
