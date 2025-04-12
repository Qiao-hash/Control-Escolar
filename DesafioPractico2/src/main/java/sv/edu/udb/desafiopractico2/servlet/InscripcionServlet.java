package sv.edu.udb.desafiopractico2.servlet;

import sv.edu.udb.desafiopractico2.dao.InscripcionDAO;
import sv.edu.udb.desafiopractico2.model.Inscripcion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import sv.edu.udb.desafiopractico2.dao.AlumnoDAO;
import sv.edu.udb.desafiopractico2.dao.MateriaDAO;
import java.util.List;
@WebServlet("/inscripciones")
public class InscripcionServlet extends HttpServlet {

    // Método doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        String action = request.getParameter("action");
        InscripcionDAO dao = new InscripcionDAO();

        try {
            if ("asociaciones".equals(action)) {
                request.setAttribute("asociaciones", dao.listarTodasInscripciones());
                request.getRequestDispatcher("/inscripciones/asociaciones.jsp").forward(request, response);
            } else if ("eliminar".equals(action)) {
                String carnet = request.getParameter("carnet");
                String codigoMateria = request.getParameter("codigoMateria");
                dao.eliminar(carnet, codigoMateria);
                response.sendRedirect(request.getContextPath() + "/inscripciones?action=asociaciones");
            }
            else if ("conteo".equals(action)) {
                request.setAttribute("conteo", dao.contarEstudiantesPorMateria());
                request.getRequestDispatcher("/inscripciones/Conteo.jsp").forward(request, response);
            } else if ("listaEstudiantes".equals(action)) {
                String codigoMateria = request.getParameter("codigoMateria");
                List<String> estudiantes = dao.listarEstudiantesPorMateria(codigoMateria);
                request.setAttribute("estudiantes", estudiantes);
                request.getRequestDispatcher("/inscripciones/listaEstudiantes.jsp").forward(request, response);

            }else {
                response.sendRedirect(request.getContextPath() + "/inscripciones/formulario.jsp");
            }
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath() + "/inscripciones/formulario.jsp?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }

    // Método doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        MateriaDAO materiaDAO = new MateriaDAO();

        try {
            String carnet = request.getParameter("carnet");
            String codigoMateria = request.getParameter("codigoMateria");

            // Validar existencia
            if (alumnoDAO.obtenerPorCarnet(carnet) == null) {
                throw new IllegalArgumentException("El alumno no existe.");
            }
            if (materiaDAO.obtenerPorCodigo(codigoMateria) == null) {
                throw new IllegalArgumentException("La materia no existe.");
            }
            if (inscripcionDAO.existeInscripcion(carnet, codigoMateria)) {
                throw new IllegalArgumentException("El alumno ya está inscrito.");
            }

            // Registrar
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setCarnetAlumno(carnet);
            inscripcion.setCodigoMateria(codigoMateria);
            inscripcionDAO.insertar(inscripcion);

            response.sendRedirect(request.getContextPath() + "/inscripciones?action=asociaciones");

        } catch (SQLException | IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/inscripciones/formulario.jsp?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        }
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}