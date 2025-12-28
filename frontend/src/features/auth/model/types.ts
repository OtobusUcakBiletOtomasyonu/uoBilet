
export const Role = {
    YOLCU: 'YOLCU',
    YONETICI: 'YONETICI',
} as const;

export type Role = (typeof Role)[keyof typeof Role];

export interface User {
    id: number;
    ad: string;
    soyad: string;
    email: string;
    role: Role;
    telefon?: string;
}

export interface LoginResponse {
    token: string;
    id: number;
    ad: string;
    soyad: string;
    email: string;
    role: Role;
    telefon?: string;
}
