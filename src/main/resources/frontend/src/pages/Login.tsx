    // src/pages/Login.tsx
    import { useState } from 'react';
    import { useNavigate } from 'react-router-dom';

    function Login() {
      const [email, setEmail] = useState('');
      const [password, setPassword] = useState('');
      const navigate = useNavigate();

      const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        const response = await fetch('/api/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const data = await response.json();
            //alert('Inicio de sesión exitoso');

            // Supongamos que el backend responde con el ID del docente:
            const idDocente = data.idDocente;

            // Redirigir a la página de clases por docente
            navigate(`/clases/docente/${idDocente}`);
        } else {
          alert('Credenciales inválidas');
        }
      };

      return (
        <div className="p-4 max-w-md mx-auto">
          <h1 className="text-2xl font-bold mb-4">Iniciar Sesión</h1>
          <form onSubmit={handleLogin} className="bg-white shadow-md rounded p-4">
            <label className="block mb-2">
              Correo electrónico:
              <input
                type="email"
                value={email}
                onChange={e => setEmail(e.target.value)}
                className="w-full p-2 border rounded"
                required
              />
            </label>

            <label className="block mb-4">
              Contraseña:
              <input
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                className="w-full p-2 border rounded"
                required
              />
            </label>

            <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded w-full">
              Iniciar Sesión
            </button>
          </form>
        </div>
      );
    }

    export default Login;