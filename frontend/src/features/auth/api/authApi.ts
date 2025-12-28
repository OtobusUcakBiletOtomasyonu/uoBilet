
import axiosInstance from '../../../core/api/axiosInstance';
import { Role, type LoginResponse } from '../model/types';
import { z } from 'zod';

// Zod Schemas for Validation
export const loginSchema = z.object({
    email: z.string().email('Geçerli bir email adresi giriniz'),
    password: z.string().min(1, 'Şifre zorunludur'),
});

export const registerSchema = z.object({
    ad: z.string().min(2, 'Ad en az 2 karakter olmalıdır'),
    soyad: z.string().min(2, 'Soyad en az 2 karakter olmalıdır'),
    email: z.string().email('Geçerli bir email adresi giriniz'),
    password: z.string().min(6, 'Şifre en az 6 karakter olmalıdır'),
    telefon: z.string().min(10, 'Telefon numarası en az 10 haneli olmalıdır'),
});

export type LoginArgs = z.infer<typeof loginSchema>;
export type RegisterArgs = z.infer<typeof registerSchema>;

export const authApi = {
    login: async (data: LoginArgs) => {
        const response = await axiosInstance.post<{ data: LoginResponse; success: boolean; message: string }>('/api/auth/login', {
            email: data.email,
            password: data.password,
        });
        return response.data;
    },

    register: async (data: RegisterArgs) => {
        const response = await axiosInstance.post('/api/auth/register', {
            ...data,
            sifre: data.password, // Mapping DTO field name
        });
        return response.data;
    },
};
