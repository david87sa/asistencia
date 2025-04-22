// src/main.tsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import App from './App';
import ListaEstudiantesPorSeccion from './pages/ListaEstudiantesPorSeccion';
import Login from './pages/Login';
import ListaClasesPorDocente from './pages/ListaClasesPorDocente';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/estudiantes/seccion" element={<ListaEstudiantesPorSeccion />} />
        <Route path="/login" element={<Login />} />
        <Route path="/clases/docente/:idDocente" element={<ListaClasesPorDocente />} />
        <Route path="/clases/:idClase/estudiantes" element={<ListaEstudiantesPorSeccion />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
