import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // para redirigir al login

const ColaboradorForm = () => {
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');
  const [celular, setCelular] = useState('');
  const [email, setEmail] = useState('');
  const [genero, setGenero] = useState('');
  const [inactivo, setInactivo] = useState(false);
  const [selectedMechanicalTasks, setSelectedMechanicalTasks] = useState([]);
  const [selectedAssignments, setSelectedAssignments] = useState([]);
  const [colaboradores, setColaboradores] = useState([]);
  const [searchNombre, setSearchNombre] = useState('');
  const [searchApellido, setSearchApellido] = useState('');
  const [selectedColaborador, setSelectedColaborador] = useState(null);

  const [mechanicalTasks, setMechanicalTasks] = useState([]); // Estado para las tareas mecánicas
  const [assignments, setAssignments] = useState([]); // Estado para las asignaciones

  const navigate = useNavigate(); // para redirigir al login

  // Obtener el token de autenticación desde el almacenamiento local
  const getToken = () => localStorage.getItem('authToken');

  // Interceptor de Axios para adjuntar el token en cada petición
  useEffect(() => {
    axios.interceptors.request.use((config) => {
      const token = getToken();
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    }, (error) => Promise.reject(error));
  }, []);

  // Función para obtener colaboradores
  const fetchColaboradores = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/colaboradores');
      setColaboradores(response.data);
    } catch (error) {
      console.error('Error fetching colaboradores:', error);
      if (error.response && error.response.status === 401) {
        navigate('/login');
      }
    }
  };

  // Función para obtener tareas mecánicas y asignaciones
  const fetchTasksAndAssignments = async () => {
    try {
      const [mechanicalResponse, assignmentResponse] = await Promise.all([
        axios.get('http://localhost:8080/api/mechanicalTasks'), // Ajusta el endpoint según tu API
        axios.get('http://localhost:8080/api/assignments'), // Ajusta el endpoint según tu API
      ]);
      setMechanicalTasks(mechanicalResponse.data);
      setAssignments(assignmentResponse.data);
    } catch (error) {
      console.error('Error fetching tasks and assignments:', error);
    }
  };

  useEffect(() => {
    fetchColaboradores();
    fetchTasksAndAssignments(); // Cargar tareas mecánicas y asignaciones al montar el componente
  }, []);

  const handleSave = async () => {
    if (!nombre || !apellido || !celular || !email || !genero || selectedMechanicalTasks.length === 0) {
      alert('Por favor, complete todos los campos obligatorios y seleccione al menos una tarea mecánica.');
      return;
    }

    const colaboradorData = {
      nombre,
      apellido,
      celular,
      email,
      genero,
      inactivo,
      tareasMecanicas: selectedMechanicalTasks,
      asignaciones: selectedAssignments
    };

    try {
      if (selectedColaborador) {
        // Actualizar colaborador existente
        await axios.put(`http://localhost:8080/api/colaboradores/${selectedColaborador.id}`, colaboradorData);
        alert('Colaborador actualizado correctamente');
      } else {
        // Agregar nuevo colaborador
        await axios.post('http://localhost:8080/api/colaboradores', colaboradorData);
        alert('Colaborador registrado correctamente');
      }
      fetchColaboradores();
      clearFields();
    } catch (error) {
      console.error('Error al guardar colaborador:', error);
      alert('Hubo un problema al guardar el colaborador.');
    }
  };

  const handleEdit = (colaborador) => {
    setNombre(colaborador.nombre);
    setApellido(colaborador.apellido);
    setCelular(colaborador.celular);
    setEmail(colaborador.email);
    setGenero(colaborador.genero);
    setInactivo(colaborador.inactivo);
    setSelectedMechanicalTasks(colaborador.tareasMecanicas || []);
    setSelectedAssignments(colaborador.asignaciones || []);
    setSelectedColaborador(colaborador);
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm('¿Estás seguro de que deseas eliminar este colaborador?');
    if (confirmDelete) {
      try {
        await axios.delete(`http://localhost:8080/api/colaboradores/${id}`);
        alert('Colaborador eliminado correctamente');
        fetchColaboradores();
      } catch (error) {
        console.error('Error al eliminar colaborador:', error);
        alert('Hubo un problema al eliminar el colaborador.');
      }
    }
  };

  const handleMechanicalTaskChange = (taskId) => {
    setSelectedMechanicalTasks((prevSelected) => {
      if (prevSelected.includes(taskId)) {
        return prevSelected.filter((id) => id !== taskId);
      } else {
        return [...prevSelected, taskId];
      }
    });
  };

  const handleAssignmentChange = (assignmentId) => {
    setSelectedAssignments((prevSelected) => {
      if (prevSelected.includes(assignmentId)) {
        return prevSelected.filter((id) => id !== assignmentId);
      } else {
        return [...prevSelected, assignmentId];
      }
    });
  };

  const clearFields = () => {
    setNombre('');
    setApellido('');
    setCelular('');
    setEmail('');
    setGenero('');
    setInactivo(false);
    setSelectedMechanicalTasks([]);
    setSelectedAssignments([]);
    setSelectedColaborador(null);
  };

  const filteredColaboradores = colaboradores.filter(colaborador =>
    colaborador.nombre.toLowerCase().startsWith(searchNombre.toLowerCase()) &&
    colaborador.apellido.toLowerCase().startsWith(searchApellido.toLowerCase())
  );

  return (
    <div>
      <h2>{selectedColaborador ? 'Editar Colaborador' : 'Agregar Colaborador'}</h2>
      <input 
        type="text" 
        placeholder="Nombre" 
        value={nombre} 
        onChange={(e) => setNombre(e.target.value)} 
      />
      <input 
        type="text" 
        placeholder="Apellido" 
        value={apellido} 
        onChange={(e) => setApellido(e.target.value)} 
      />
      <input 
        type="text" 
        placeholder="Celular" 
        value={celular} 
        onChange={(e) => setCelular(e.target.value)} 
      />
      <input 
        type="email" 
        placeholder="Email" 
        value={email} 
        onChange={(e) => setEmail(e.target.value)} 
      />
      <select value={genero} onChange={(e) => setGenero(e.target.value)}>
        <option value="">Seleccione género</option>
        <option value="Femenino">Femenino</option>
        <option value="Masculino">Masculino</option>
      </select>
      <label>
        <input 
          type="checkbox" 
          checked={inactivo} 
          onChange={() => setInactivo(!inactivo)} 
        /> Inactivo
      </label>

      <h3>Tareas Mecánicas</h3>
      {mechanicalTasks.map((task) => (
        <label key={task.id}>
          <input 
            type="checkbox" 
            checked={selectedMechanicalTasks.includes(task.id)} 
            onChange={() => handleMechanicalTaskChange(task.id)} 
          />
          {task.name}
        </label>
      ))}

      <h3>Asignaciones</h3>
      {assignments.map((assignment) => (
        <label key={assignment.id}>
          <input 
            type="checkbox" 
            checked={selectedAssignments.includes(assignment.id)} 
            onChange={() => handleAssignmentChange(assignment.id)} 
          />
          {assignment.name}
        </label>
      ))}

      <button onClick={handleSave}>{selectedColaborador ? 'Actualizar' : 'Guardar'}</button>
      <button onClick={clearFields}>Limpiar</button>

      <h3>Colaboradores</h3>
      <input 
        type="text" 
        placeholder="Buscar Nombre" 
        value={searchNombre} 
        onChange={(e) => setSearchNombre(e.target.value)} 
      />
      <input 
        type="text" 
        placeholder="Buscar Apellido" 
        value={searchApellido} 
        onChange={(e) => setSearchApellido(e.target.value)} 
      />
      <ul>
        {filteredColaboradores.map((colaborador) => (
          <li key={colaborador.id}>
            {colaborador.nombre} {colaborador.apellido} 
            <button onClick={() => handleEdit(colaborador)}>Editar</button>
            <button onClick={() => handleDelete(colaborador.id)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ColaboradorForm;





