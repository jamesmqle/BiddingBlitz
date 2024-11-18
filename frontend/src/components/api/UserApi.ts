import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/user'; // Adjust based on your backend

export const registerUser = async (userData: any) => {
  return axios.post(`${API_BASE_URL}/register`, userData);
};

export const loginUser = async (credentials: any) => {
  return axios.post(`${API_BASE_URL}/login`, credentials);
};

export const getUserDetails = async (userId: string) => {
  return axios.get(`${API_BASE_URL}/${userId}`);
};