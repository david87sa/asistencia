import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { PieChart, Pie, Cell, Legend, Tooltip, ResponsiveContainer } from 'recharts';

interface Estudiante {
  id: number;
  nombre: string;
}

interface Registro {
  idEstudiante: number;
  presente: boolean;
}

const COLORS = ['#22c55e', '#ef4444'];

function ListaEstudiantesPorClase() {
  const { idClase } = useParams<{ idClase: string }>();
  const [estudiantes, setEstudiantes] = useState<Estudiante[]>([]);
  const [registros, setRegistros] = useState<Registro[]>([]);
  const [registroExitoso, setRegistroExitoso] = useState<number | null>(null);

  useEffect(() => {
    if (idClase) {
      fetch(`/api/clases/${idClase}/estudiantes`)
        .then(res => res.json())
        .then(data => setEstudiantes(data));

      fetch(`/api/asistencia/clase/${idClase}`)
        .then(res => res.json())
        .then(data => setRegistros(data));
    }
  }, [idClase]);

  const registrarAsistencia = (idEstudiante: number, presente: boolean) => {
    fetch('/api/asistencia/registrar', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        idEstudiante,
        idClase: Number(idClase),
        presente,
        observaciones: ''
      })
    }).then(res => {
      if (res.ok) {
        setRegistroExitoso(idEstudiante);
        setRegistros(prev => {
          const updated = prev.filter(r => r.idEstudiante !== idEstudiante);
          return [...updated, { idEstudiante, presente }];
        });
        setTimeout(() => setRegistroExitoso(null), 2000);
      } else {
        alert('Error al registrar asistencia');
      }
    });
  };

  const totalPresentes = registros.filter(r => r.presente).length;
  const totalAusentes = registros.filter(r => !r.presente).length;
  const dataChart = [
    { name: 'Presentes', value: totalPresentes },
    { name: 'Ausentes', value: totalAusentes }
  ];

  const getEstado = (idEstudiante: number): 'presente' | 'ausente' | null => {
    const registro = registros.find(r => r.idEstudiante === idEstudiante);
    if (!registro) return null;
    return registro.presente ? 'presente' : 'ausente';
  };

  return (
    <div className="p-4 max-w-2xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Estudiantes de la Clase #{idClase}</h1>

      {estudiantes.length > 0 ? (
        <>
          <ul className="space-y-2">
            {estudiantes.map(est => {
              const estado = getEstado(est.id);
              return (
                <li key={est.id} className="flex justify-between items-center border p-2 rounded">
                  <span>{est.nombre}</span>
                  <div className="space-x-2">
                    <button
                      onClick={() => registrarAsistencia(est.id, true)}
                      disabled={estado === 'presente'}
                      className={`px-3 py-1 rounded ${
                        estado === 'presente'
                          ? 'bg-green-800 text-white cursor-default'
                          : 'bg-green-600 text-white hover:bg-green-700'
                      }`}
                    >
                      Presente
                    </button>
                    <button
                      onClick={() => registrarAsistencia(est.id, false)}
                      disabled={estado === 'ausente'}
                      className={`px-3 py-1 rounded ${
                        estado === 'ausente'
                          ? 'bg-red-800 text-white cursor-default'
                          : 'bg-red-600 text-white hover:bg-red-700'
                      }`}
                    >
                      Ausente
                    </button>
                    {registroExitoso === est.id && (
                      <span className="text-sm text-green-700 ml-2">✓ Registrado</span>
                    )}
                  </div>
                </li>
              );
            })}
          </ul>

          <div className="mt-6 p-4 bg-gray-100 rounded">
            <h2 className="text-lg font-semibold mb-4">Resumen de asistencia</h2>
            <div className="h-60">
              <ResponsiveContainer>
                <PieChart>
                  <Pie
                    data={dataChart}
                    dataKey="value"
                    nameKey="name"
                    cx="50%"
                    cy="50%"
                    outerRadius={80}
                    label
                  >
                    <Cell fill={COLORS[0]} />
                    <Cell fill={COLORS[1]} />
                  </Pie>
                  <Tooltip />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </div>
          </div>
        </>
      ) : (
        <p>No se encontraron estudiantes para esta clase.</p>
      )}
    </div>
  );
}

export default ListaEstudiantesPorClase;