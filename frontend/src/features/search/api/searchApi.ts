
import axiosInstance from '../../../core/api/axiosInstance';
import type { SeferDTO, SearchParams } from '../model/types';

export const searchApi = {
    searchSefer: async (params: SearchParams) => {
        const response = await axiosInstance.get<{ data: SeferDTO[]; success: boolean; message: string }>('/api/seferler/search', {
            params,
        });
        return response.data;
    },

    getAllSeferler: async () => {
        const response = await axiosInstance.get<{ data: SeferDTO[]; success: boolean; message: string }>(
            '/api/seferler'
        );
        return response.data;
    },

    getKoltuklar: async (seferId: number) => {
        const response = await axiosInstance.get<{ data: import('../model/types').KoltukDTO[]; success: boolean; message: string }>(
            `/api/koltuklar/sefer/${seferId}`
        );
        return response.data;
    },
};
