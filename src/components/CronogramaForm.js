import React, { useState } from 'react';

const CronogramaForm = () => {
  const [titulo, setTitulo] = useState('');
  const [intervenciones, setIntervenciones] = useState([]);
  const [nuevaIntervencion, setNuevaIntervencion] = useState({
    tipo: '',
    ayudante: '',
    fecha: ''
  });

  const tareasMecanicas = [
    'Acomodador',
    'Microfonista',
    'Consola de audio',
    'Manejo de multimedia',
    'Encargado de audio de plataforma'
  ];

  const handleAgregarIntervencion = () => {
    if (nuevaIntervencion.tipo && nuevaIntervencion.fecha) {
      setIntervenciones([...intervenciones, nuevaIntervencion]);
      setNuevaIntervencion({ tipo: '', ayudante: '', fecha: '' });
    } else {
      alert('Por favor, complete todos los campos.');
    }
  };

  return (
    <div>
      <h2>Cargar Cronograma</h2>
      <input
        type="text"
        placeholder="Título del Cronograma"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
      />
      <h3>Agregar Intervención</h3>
      <select
        value={nuevaIntervencion.tipo}
        onChange={(e) => setNuevaIntervencion({ ...nuevaIntervencion, tipo: e.target.value })}
      >
        <option value="">Selecciona una Tarea Mecánica</option>
        {tareasMecanicas.map((tarea, index) => (
          <option key={index} value={tarea}>{tarea}</option>
        ))}
      </select>
      <input
        type="text"
        placeholder="Ayudante (opcional)"
        value={nuevaIntervencion.ayudante}
        onChange={(e) => setNuevaIntervencion({ ...nuevaIntervencion, ayudante: e.target.value })}
      />
      <input
        type="date"
        value={nuevaIntervencion.fecha}
        onChange={(e) => setNuevaIntervencion({ ...nuevaIntervencion, fecha: e.target.value })}
      />
      <button onClick={handleAgregarIntervencion}>Agregar Intervención</button>

      <h3>Cronograma</h3>
      {titulo && <h4>{titulo}</h4>}
      <ul>
        {intervenciones.map((intervencion, index) => (
          <li key={index}>
            {intervencion.tipo} - {intervencion.ayudante} - {intervencion.fecha}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CronogramaForm;
