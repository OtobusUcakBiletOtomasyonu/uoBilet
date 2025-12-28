
import axiosInstance from '../../../core/api/axiosInstance';
import type { Firma } from '../../search/model/types';

export interface CreateSeferRequest {
    kalkisYeri: string;
    varisYeri: string;
    kalkisZamani: string; // ISO
    fiyat: number;
    tip: 'OTOBUS' | 'UCAK';
    koltukDuzeni?: string;
    firmaId?: number;
}

export const adminApi = {
    createFirma: async (ad: string) => {
        const response = await axiosInstance.post<{ data: Firma; success: boolean; message: string }>(
            `/api/firmalar?ad=${encodeURIComponent(ad)}`
        );
        return response.data;
    },

    getAllFirmalar: async () => {
        const response = await axiosInstance.get<{ data: Firma[]; success: boolean; message: string }>(
            '/api/firmalar'
        );
        return response.data;
    },

    createSefer: async (request: CreateSeferRequest) => {
        const response = await axiosInstance.post<{ data: number; success: boolean; message: string }>(
            '/api/seferler',
            request
        );
        return response.data;
    },
};
