import React, { useEffect, useState } from 'react';
import { fetchActos } from '../api';

const ReminderList = () => {
    const [actos, setActos] = useState([]);

    useEffect(() => {
        const getActos = async () => {
            const data = await fetchActos();
            setActos(data);
        };
        getActos();
    }, []);

    return (
        <div>
            <h2>Recordatorios</h2>
            {actos.map(acto => (
                <div key={acto.id}>
                    <h3>{acto.titulo}</h3>
                    <p>Fecha: {acto.fecha}</p>
                </div>
            ))}
        </div>
    );
};

export default ReminderList;
