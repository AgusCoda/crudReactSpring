import React, { useState } from 'react';
import { createActo } from '../api';

const ActoForm = () => {
    const [titulo, setTitulo] = useState('');
    const [fecha, setFecha] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await createActo({ titulo, fecha });
        // Aquí podrías agregar lógica para manejar la respuesta y actualizar la UI
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Título del Acto"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
                required
            />
            <input
                type="date"
                value={fecha}
                onChange={(e) => setFecha(e.target.value)}
                required
            />
            <button type="submit">Agregar Acto</button>
        </form>
    );
};

export default ActoForm;
