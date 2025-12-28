
import axiosInstance from '../../../core/api/axiosInstance';

export interface BiletSatinAlRequest {
    yolcuId: number;
    koltukId: number;
    odemeStrategy: 'KREDI_KARTI' | 'HAVALE'; // Simplified
    tutar: number;
}

export const paymentApi = {
    satinAl: async (request: BiletSatinAlRequest) => {
        const response = await axiosInstance.post<{ data: number; success: boolean; message: string }>( // Returns Ticket ID
            '/api/biletler/satin-al',
            request
        );
        return response.data;
    },
};
