// src/App.tsx
import { useEffect, useState, FormEvent } from 'react';
import { Link } from 'react-router-dom';

interface Estudiante {
  id: number;
  nombre: string;
}

interface Clase {
  id: number;
  fecha: string;
  horaInicio: string;
  horaFin: string;
}

interface RegistroAsistencia {
  idEstudiante: number;
  idClase: number;
  presente: boolean;
  observaciones: string;
}

function App() {
  const [estudiantes, setEstudiantes] = useState<Estudiante[]>([]);
  const [clases, setClases] = useState<Clase[]>([]);
  const [idEstudiante, setIdEstudiante] = useState<number | "">("");
  const [idClase, setIdClase] = useState<number | "">("");
  const [presente, setPresente] = useState<boolean>(true);
  const [observaciones, setObservaciones] = useState<string>("");

  useEffect(() => {
    fetch('/api/estudiantes')
      .then(res => res.json())
      .then(data => setEstudiantes(data));

    fetch('/api/clases')
      .then(res => res.json())
      .then(data => setClases(data));
  }, []);

  const registrarAsistencia = async (e: FormEvent) => {
    e.preventDefault();

    if (idEstudiante === "" || idClase === "") {
      alert("Debe seleccionar estudiante y clase");
      return;
    }

    const data: RegistroAsistencia = {
      idEstudiante: Number(idEstudiante),
      idClase: Number(idClase),
      presente,
      observaciones,
    };

    const res = await fetch('/api/asistencia/registrar', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });

    if (res.ok) {
      alert('Asistencia registrada correctamente');
      setObservaciones("");
    } else {
      alert('Error al registrar asistencia');
    }
  };

  return (
    <div className="p-4 max-w-xl mx-auto">
    <Link to="/estudiantes/seccion" className="text-blue-500 underline">
      Ver estudiantes por sección
    </Link>
      <h1 className="text-2xl font-bold mb-4">Registrar Asistencia</h1>
      <form onSubmit={registrarAsistencia} className="bg-white shadow-md rounded p-4">
        <label className="block mb-2">
          Estudiante:
          <select
            value={idEstudiante}
            onChange={e => setIdEstudiante(Number(e.target.value))}
            className="w-full p-2 border rounded"
            required
          >
            <option value="">Seleccione</option>
            {estudiantes.map(est => (
              <option key={est.id} value={est.id}>{est.nombre}</option>
            ))}
          </select>
        </label>

        <label className="block mb-2">
          Clase:
          <select
            value={idClase}
            onChange={e => setIdClase(Number(e.target.value))}
            className="w-full p-2 border rounded"
            required
          >
            <option value="">Seleccione</option>
            {clases.map(cl => (
              <option key={cl.id} value={cl.id}>
                {cl.fecha} ({cl.horaInicio} - {cl.horaFin})
              </option>
            ))}
          </select>
        </label>

        <label className="block mb-2">
          Presente:
          <input
            type="checkbox"
            checked={presente}
            onChange={e => setPresente(e.target.checked)}
            className="ml-2"
          />
        </label>

        <label className="block mb-4">
          Observaciones:
          <textarea
            value={observaciones}
            onChange={e => setObservaciones(e.target.value)}
            className="w-full p-2 border rounded"
            rows={3}
          />
        </label>

        <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
          Registrar
        </button>
      </form>
    </div>
  );
}

export default App;