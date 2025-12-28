import { useMemo, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { searchApi } from '../api/searchApi';
import TripCard from '../components/TripCard';
import type { SeferDTO } from '../model/types';
import './SearchPage.css';

const SearchPage = () => {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();

    const kalkis = searchParams.get('kalkis') || '';
    const varis = searchParams.get('varis') || '';
    const tarih = searchParams.get('tarih') || '';

    // Local Filter States
    const [maxPrice, setMaxPrice] = useState<number>(5000);
    const [selectedFirm, setSelectedFirm] = useState<string>('');

    const { data, isLoading, isError } = useQuery({
        queryKey: ['seferler', kalkis, varis, tarih],
        queryFn: () => searchApi.searchSefer({ kalkis, varis, zaman: new Date(tarih).toISOString() }),
        enabled: !!kalkis && !!varis && !!tarih,
    });

    const filteredSeferler = useMemo(() => {
        if (!data?.data) return [];
        return data.data.filter((sefer) => {
            const priceMatch = sefer.fiyat <= maxPrice;
            const firmMatch = selectedFirm ? sefer.firmaAdi.toLowerCase().includes(selectedFirm.toLowerCase()) : true;
            return priceMatch && firmMatch;
        });
    }, [data, maxPrice, selectedFirm]);

    const handleSelectSefer = (sefer: SeferDTO) => {
        navigate(`/trip/${sefer.id}`);
    };

    return (
        <div className="search-page">
            <div className="container">
                <div className="search-header">
                    <h1 className="search-title">{kalkis} - {varis} Seferleri</h1>
                    <p className="search-subtitle">{tarih} Tarihli Seferler</p>
                </div>

                <div className="search-layout">
                    {/* Filters Sidebar */}
                    <aside className="filters-sidebar">
                        <h3 className="filter-title">Filtrele</h3>

                        <div className="filter-group">
                            <label className="text-sm font-medium mb-sm block">Fiyat Aralığı (Max: {maxPrice} TL)</label>
                            <input
                                type="range"
                                min="0"
                                max="5000"
                                step="50"
                                value={maxPrice}
                                onChange={(e) => setMaxPrice(Number(e.target.value))}
                                className="range-slider"
                            />
                        </div>

                        <div className="filter-group">
                            <label className="text-sm font-medium mb-sm block">Firma Ara</label>
                            <input
                                type="text"
                                className="filter-input"
                                placeholder="Firma adı..."
                                value={selectedFirm}
                                onChange={(e) => setSelectedFirm(e.target.value)}
                            />
                        </div>
                    </aside>

                    {/* Results List */}
                    <main className="results-list">
                        {isLoading && <div className="text-center py-lg">Seferler aranıyor...</div>}

                        {isError && (
                            <div className="card text-center text-error">
                                Seferler yüklenirken bir hata oluştu.
                            </div>
                        )}

                        {!isLoading && !isError && filteredSeferler.length === 0 && (
                            <div className="card text-center py-lg">
                                Aradığınız kriterlere uygun sefer bulunamadı.
                            </div>
                        )}

                        {filteredSeferler.map((sefer) => (
                            <TripCard key={sefer.id} sefer={sefer} onSelect={handleSelectSefer} />
                        ))}
                    </main>
                </div>
            </div>
        </div>
    );
};

export default SearchPage;
