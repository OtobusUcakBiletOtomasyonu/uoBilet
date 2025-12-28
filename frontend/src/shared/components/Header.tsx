
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../features/auth/model/AuthContext';
import './Layout.css';

const Header = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { user, logout } = useAuth();

    return (
        <header className="app-header">
            <div className="header-container">
                <div className="flex items-center gap-md">
                    <a href="/" onClick={(e) => { e.preventDefault(); navigate('/'); }} className="logo">
                        Ticket App
                    </a>

                    <nav className="nav-links">
                        <a
                            href="/"
                            onClick={(e) => { e.preventDefault(); navigate('/'); }}
                            className={`nav-link ${location.pathname === '/' ? 'active' : ''}`}
                        >
                            Otobüs Bileti
                        </a>
                        <a
                            href="/"
                            onClick={(e) => { e.preventDefault(); navigate('/?type=plane'); }} // Placeholder query param
                            className={`nav-link`}
                        >
                            Uçak Bileti
                        </a>
                    </nav>
                </div>

                <div className="user-actions">
                    {user ? (
                        <>
                            {user.role === 'YONETICI' && (
                                <>
                                    <button onClick={() => navigate('/admin/create-firm')} className="header-btn">
                                        Firma Ekle
                                    </button>
                                    <button onClick={() => navigate('/admin/create-trip')} className="header-btn">
                                        Sefer Ekle
                                    </button>
                                </>
                            )}

                            <button onClick={() => navigate('/my-tickets')} className="header-btn">
                                Seferlerim
                            </button>
                            <button onClick={() => navigate('/profile')} className="header-btn">
                                Hesabım
                            </button>
                            <button onClick={logout} className="header-btn" style={{ color: 'var(--error-color)' }}>
                                Çıkış
                            </button>
                        </>
                    ) : (
                        <>
                            <button onClick={() => navigate('/register')} className="header-btn">
                                Üye Ol
                            </button>
                            <button onClick={() => navigate('/login')} className="btn btn-secondary" style={{ padding: '8px 24px' }}>
                                Üye Girişi
                            </button>
                        </>
                    )}
                </div>
            </div>
        </header>
    );
};

export default Header;
