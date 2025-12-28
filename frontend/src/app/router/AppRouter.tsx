
import React from 'react';
import { createBrowserRouter, RouterProvider, Navigate } from 'react-router-dom';
import LoginPage from '../../features/auth/pages/LoginPage';
import RegisterPage from '../../features/auth/pages/RegisterPage';
import HomePage from '../../features/search/pages/HomePage';
import SearchPage from '../../features/search/pages/SearchPage';
import TripDetailPage from '../../features/search/pages/TripDetailPage';
import PaymentPage from '../../features/payment/pages/PaymentPage';
import MyTicketsPage from '../../features/pages/MyTicketsPage';
import ProfilePage from '../../features/pages/ProfilePage';
import CreateFirmaPage from '../../features/admin/pages/CreateFirmaPage';
import CreateSeferPage from '../../features/admin/pages/CreateSeferPage';
import MainLayout from '../../shared/components/MainLayout';
import { useAuth } from '../../features/auth/model/AuthContext';

// Protected Route Wrapper with Layout
const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
    const { isAuthenticated } = useAuth();
    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }
    return <MainLayout>{children}</MainLayout>;
};

const router = createBrowserRouter([
    {
        path: '/',
        element: (
            <ProtectedRoute>
                <HomePage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/search',
        element: (
            <ProtectedRoute>
                <SearchPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/trip/:id',
        element: (
            <ProtectedRoute>
                <TripDetailPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/payment',
        element: (
            <ProtectedRoute>
                <PaymentPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/my-tickets',
        element: (
            <ProtectedRoute>
                <MyTicketsPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/profile',
        element: (
            <ProtectedRoute>
                <ProfilePage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/admin/create-firm',
        element: (
            <ProtectedRoute>
                <CreateFirmaPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/admin/create-trip',
        element: (
            <ProtectedRoute>
                <CreateSeferPage />
            </ProtectedRoute>
        ),
    },
    {
        path: '/login',
        element: <LoginPage />,
    },
    {
        path: '/register',
        element: <RegisterPage />,
    },
    {
        path: '*',
        element: <div>404 Not Found</div>,
    },
]);

export const AppRouter = () => {
    return <RouterProvider router={router} />;
};
