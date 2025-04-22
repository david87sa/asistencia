// src/pages/ListaClasesPorDocente.tsx
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

interface Clase {
  id: number;
  fecha: string;
  horaInicio: string;
  horaFin: string;
  seccion: {
    id: number;
    nombre: string;
  };
}

function ListaClasesPorDocente() {
  const { idDocente } = useParams<{ idDocente: string }>();
  const [clases, setClases] = useState<Clase[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (idDocente) {
      fetch(`/api/clases/docente/${idDocente}`)
        .then(res => res.json())
        .then(data => setClases(data));
    }
  }, [idDocente]);

  const irAEstudiantes = (idClase: number) => {
    navigate(`/clases/${idClase}/estudiantes`);
  };

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Clases del Docente #{idDocente}</h1>

      {clases.length > 0 ? (
        <table className="w-full table-auto border">
          <thead>
            <tr className="bg-gray-100">
              <th className="p-2 border">Fecha</th>
              <th className="p-2 border">Horario</th>
              <th className="p-2 border">Sección</th>
            </tr>
          </thead>
          <tbody>
            {clases.map(clase => (
              <tr
                key={clase.id}
                className="cursor-pointer hover:bg-blue-100"
                onClick={() => irAEstudiantes(clase.id)}
              >
                <td className="p-2 border">{clase.fecha}</td>
                <td className="p-2 border">{clase.horaInicio} - {clase.horaFin}</td>
                <td className="p-2 border">{clase.seccion.nombre}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No se encontraron clases para este docente.</p>
      )}
    </div>
  );
}

export default ListaClasesPorDocente;
