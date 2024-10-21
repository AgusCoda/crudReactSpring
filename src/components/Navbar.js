import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav>
            <ul>
                <li><Link to="/cronograma">Cronograma</Link></li>
                <li><Link to="/acto">Acto</Link></li>
                <li><Link to="/colaboradores">Colaboradores</Link></li>
                <li><Link to="/recordatorios">Recordatorios</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;

