'use server';

import axios, {
    AxiosError,
    AxiosInstance,
    AxiosRequestConfig,
    AxiosRequestHeaders,
    InternalAxiosRequestConfig,
} from 'axios';
import { cookies } from 'next/headers';

const config: AxiosRequestConfig = {
    baseURL: process.env.API_URL,
};

const http: AxiosInstance = axios.create(config);

const onRequest = (
    config: InternalAxiosRequestConfig
): InternalAxiosRequestConfig => {
    const accessToken = cookies().get('__session')?.value;
    config.headers = {
        ...config.headers,
        Authorization: `Bearer ${accessToken}`,
    } as AxiosRequestHeaders;
    return config;
};

const onRequestError = (error: AxiosError): Promise<AxiosError> => {
    return Promise.reject(error);
};

// Request interceptor
http.interceptors.request.use(onRequest, onRequestError);

export default http;
