import React from 'react';
import Header from './Header';
import './Layout.css';

interface LayoutProps {
    children: React.ReactNode;
}

const MainLayout = ({ children }: LayoutProps) => {
    return (
        <div className="flex flex-col min-h-screen">
            <Header />
            <main className="app-main">
                {children}
            </main>
            <footer className="app-footer">
                <div className="footer-grid">
                    <div className="footer-col">
                        <h4>Ticket App</h4>
                        <ul>
                            <li><a href="#">Hakkımızda</a></li>
                            <li><a href="#">S.S.S</a></li>
                            <li><a href="#">İletişim</a></li>
                        </ul>
                    </div>
                    <div className="footer-col">
                        <h4>Bilet İşlemleri</h4>
                        <ul>
                            <li><a href="#">Otobüs Bileti Al</a></li>
                            <li><a href="#">Uçak Bileti Al</a></li>
                            <li><a href="#">Sefer Sorgula</a></li>
                        </ul>
                    </div>
                    <div className="footer-col">
                        <h4>Üyelik</h4>
                        <ul>
                            <li><a href="/login">Giriş Yap</a></li>
                            <li><a href="/register">Üye Ol</a></li>
                            <li><a href="/my-tickets">Biletlerim</a></li>
                        </ul>
                    </div>
                </div>
                <div className="text-center mt-md text-secondary" style={{ opacity: 0.6 }}>
                    &copy; {new Date().getFullYear()} Ticket App. Tüm hakları saklıdır.
                </div>
            </footer>
        </div>
    );
};

export default MainLayout;
