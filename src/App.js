import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import ActoForm from './components/ActoForm';
import ColaboradorForm from './components/ColaboradorForm';
import ReminderList from './components/ReminderList';
import CronogramaForm from './components/CronogramaForm';

function App() {
    return (
        <Router>
            <div>
                <Navbar />
                <h1>Gestión de Actos y Colaboradores</h1>
                <Routes>
                    <Route path="/cronograma" element={<CronogramaForm />} />
                    <Route path="/acto" element={<ActoForm />} />
                    <Route path="/colaboradores" element={<ColaboradorForm />} />
                    <Route path="/recordatorios" element={<ReminderList />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;

