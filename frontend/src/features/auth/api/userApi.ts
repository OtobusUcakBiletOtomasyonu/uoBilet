
import axiosInstance from '../../../core/api/axiosInstance';
import type { Bilet } from '../../search/model/types';

export const userApi = {
    getMyTickets: async (yolcuId: number) => {
        const response = await axiosInstance.get<{ data: Bilet[]; success: boolean; message: string }>(
            `/api/biletler/yolcu/${yolcuId}`
        );
        return response.data;
    },
};
