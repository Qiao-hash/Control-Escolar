package sv.edu.udb.desafiopractico2.servlet;

import sv.edu.udb.desafiopractico2.dao.AlumnoDAO;
import sv.edu.udb.desafiopractico2.model.Alumno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/alumnos")
public class AlumnoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DEBUG] Método doGet ejecutado");
        String action = request.getParameter("action");
        System.out.println("[DEBUG] Acción recibida (GET): " + action);
        AlumnoDAO dao = new AlumnoDAO();

        try {
            if ("edit".equals(action)) {
                // Cargar alumno para edición
                String carnet = request.getParameter("carnet");
                Alumno alumno = dao.obtenerPorCarnet(carnet);
                request.setAttribute("alumno", alumno);
                request.getRequestDispatcher("/alumnos/formulario.jsp").forward(request, response);
            } else {
                // Listar todos los alumnos
                request.setAttribute("alumnos", dao.listar());
                request.getRequestDispatcher("/alumnos/listar.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            manejarError(request, response, "Error de base de datos: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DEBUG] Método doPost ejecutado");
        String action = request.getParameter("action");
        System.out.println("[DEBUG] Acción recibida (POST): " + action);
        AlumnoDAO dao = new AlumnoDAO();

        try {
            switch (action) {
                case "insertar":
                    insertarAlumno(request, dao);
                    break;
                case "actualizar":
                    actualizarAlumno(request, dao);
                    break;
                case "delete":
                    eliminarAlumno(request, dao);
                    break;


            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/alumnos");
    }

    private void insertarAlumno(HttpServletRequest request, AlumnoDAO dao)
            throws SQLException, ParseException {

        Alumno alumno = new Alumno();
        alumno.setCarnet(request.getParameter("carnet"));
        mapearParametros(request, alumno);
        dao.insertar(alumno);
    }

    private void actualizarAlumno(HttpServletRequest request, AlumnoDAO dao)
            throws SQLException, ParseException {

        Alumno alumno = dao.obtenerPorCarnet(request.getParameter("carnet"));
        mapearParametros(request, alumno);
        dao.actualizar(alumno);
    }

    private void eliminarAlumno(HttpServletRequest request, AlumnoDAO dao)
            throws SQLException {

        String carnet = request.getParameter("carnet");
        dao.eliminar(carnet);
    }

    private void mapearParametros(HttpServletRequest request, Alumno alumno) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = request.getParameter("fechaNacimiento");

        if (fechaStr == null || fechaStr.isEmpty()) {
            throw new ParseException("Fecha vacía", 0);
        }
        alumno.setFechaNacimiento(sdf.parse(fechaStr));

        // Resto de campos...
        alumno.setNombre(request.getParameter("nombre"));
        alumno.setApellidos(request.getParameter("apellidos"));
        alumno.setDireccion(request.getParameter("direccion"));
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}