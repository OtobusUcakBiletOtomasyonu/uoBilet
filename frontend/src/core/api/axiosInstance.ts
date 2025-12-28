
import axios from 'axios';

// Environment variable for API Base URL
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:9090';

const axiosInstance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Request Interceptor to add Token
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Response Interceptor for 401 handling
axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            // Token expired or invalid
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            // Optionally redirect to login or dispatch a logout event
            // window.location.href = '/login'; 
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
