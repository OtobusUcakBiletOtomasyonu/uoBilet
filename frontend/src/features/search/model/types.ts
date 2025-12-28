
export interface SeferDTO {
    id: number;
    kalkisYeri: string;
    varisYeri: string;
    kalkisZamani: string; // ISO String
    fiyat: number;
    tip: 'OTOBUS' | 'UCAK';
    firmaAdi: string;
    koltukDuzeni?: string;
}

export interface Firma {
    id: number;
    ad: string;
}

export interface Bilet {
    id: number;
    yolcuId: number;
    koltuk: KoltukDTO;
    satinAlmaTarihi: string;
    durum: 'AKTIF' | 'IPTAL';
    tutar?: number; // Backend might need to send this
}

export interface KoltukDTO {
    id: number;
    numara: number;
    durum: 'BOS' | 'DOLU' | 'REZERVE';
    seferId: number;
}

export interface SearchParams {
    kalkis: string;
    varis: string;
    zaman: string; // ISO date-time
}
