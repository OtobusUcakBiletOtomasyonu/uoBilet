import { useQuery } from '@tanstack/react-query';
import { useAuth } from '../auth/model/AuthContext';
import { userApi } from '../auth/api/userApi';
import type { Bilet } from '../search/model/types';
import './MyTicketsPage.css';

const MyTicketsPage = () => {
    const { user } = useAuth();

    const { data, isLoading, isError } = useQuery({
        queryKey: ['myTickets', user?.id],
        queryFn: () => userApi.getMyTickets(user!.id),
        enabled: !!user,
    });

    if (!user) {
        return (
            <div className="container" style={{ marginTop: '20px' }}>
                <div className="alert alert-warning">Lütfen giriş yapınız.</div>
            </div>
        );
    }

    return (
        <div className="my-tickets-page">
            <div className="container">
                <h1 className="text-2xl font-bold text-center mb-xl">Seferlerim</h1>

                {isLoading && <div className="text-center">Yükleniyor...</div>}

                {isError && (
                    <div className="alert alert-error">Biletler yüklenirken hata oluştu.</div>
                )}

                {!isLoading && !isError && data?.data?.length === 0 && (
                    <div className="empty-state">
                        <p>Henüz bir biletiniz bulunmuyor.</p>
                    </div>
                )}

                <div className="ticket-list">
                    {data?.data?.map((bilet: Bilet) => (
                        <div key={bilet.id} className="ticket-card">
                            <div className="ticket-header">
                                <span className="font-bold">Bilet No: #{bilet.id}</span>
                                <span className="text-sm opacity-80">{new Date(bilet.satinAlmaTarihi).toLocaleDateString()}</span>
                            </div>
                            <div className="ticket-body">
                                <div className="ticket-info-item">
                                    <span className="ticket-label">Koltuk</span>
                                    <span className="ticket-value">{bilet.koltuk?.numara || '-'}</span>
                                </div>
                                <div className="ticket-info-item">
                                    <span className="ticket-label">Tarih/Saat</span>
                                    <span className="ticket-value">{new Date(bilet.satinAlmaTarihi).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</span>
                                </div>
                                <div className="ticket-info-item">
                                    <span className="ticket-label">Durum</span>
                                    <span className={`ticket-status ${bilet.durum === 'AKTIF' ? 'active' : 'cancelled'}`}>
                                        {bilet.durum}
                                    </span>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default MyTicketsPage;
