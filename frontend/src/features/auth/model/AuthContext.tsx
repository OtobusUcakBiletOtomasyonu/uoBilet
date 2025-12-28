
import React, { createContext, useContext, useState, useEffect } from 'react';
import type { User, LoginResponse } from '../model/types';

interface AuthContextType {
    user: User | null;
    isAuthenticated: boolean;
    login: (data: LoginResponse) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(() => {
        try {
            const storedUser = localStorage.getItem('user');
            return storedUser ? JSON.parse(storedUser) : null;
        } catch (e) {
            return null;
        }
    });

    // useEffect removed since lazy init handles it synchronously

    const login = (data: LoginResponse) => {
        const userObj: User = {
            id: data.id,
            ad: data.ad,
            soyad: data.soyad,
            email: data.email,
            role: data.role,
            telefon: data.telefon,
        };

        localStorage.setItem('token', data.token);
        localStorage.setItem('user', JSON.stringify(userObj));
        setUser(userObj);
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        setUser(null);
        window.location.href = '/login';
    };

    return (
        <AuthContext.Provider value={{ user, isAuthenticated: !!user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
