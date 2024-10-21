import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Cambia esto según tu configuración

export const fetchActos = async () => {
    const response = await axios.get(`${API_URL}/actos`);
    return response.data;
};

export const createActo = async (acto) => {
    const response = await axios.post(`${API_URL}/actos`, acto);
    return response.data;
};

export const createColaborador = async (colaborador) => {
    const response = await axios.post(`${API_URL}/colaboradores`, colaborador);
    return response.data;
};
