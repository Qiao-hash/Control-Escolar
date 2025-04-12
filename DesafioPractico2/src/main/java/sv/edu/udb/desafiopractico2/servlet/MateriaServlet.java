package sv.edu.udb.desafiopractico2.servlet;

import sv.edu.udb.desafiopractico2.dao.MateriaDAO;
import sv.edu.udb.desafiopractico2.model.Materia;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/materias")
public class MateriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        MateriaDAO dao = new MateriaDAO();

        try {
            if("edit".equals(action)) {
                String codigo = request.getParameter("codigo");
                Materia materia = dao.obtenerPorCodigo(codigo);
                request.setAttribute("materia", materia);
                request.getRequestDispatcher("/materias/formulario.jsp").forward(request, response);
            } else {
                request.setAttribute("materias", dao.listar());
                request.getRequestDispatcher("/materias/listar.jsp").forward(request, response);
            }
        } catch(SQLException e) {
            manejarError(request, response, "Error de BD: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[DEBUG] Método doPost ejecutado (Materias)");
        String action = request.getParameter("action");
        System.out.println("[DEBUG] Acción recibida (POST): " + action);
        MateriaDAO dao = new MateriaDAO();

        try {
            switch (action) {
                case "insertar":
                    insertarMateria(request, dao);
                    break;
                case "actualizar":
                    actualizarMateria(request, dao);
                    break;
                case "eliminar":
                    eliminarMateria(request, dao);
                    break;
                default:
                    throw new IllegalArgumentException("Acción no válida: " + action);
            }
        } catch (SQLException | ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/materias");
    }

    private void insertarMateria(HttpServletRequest request, MateriaDAO dao)
            throws SQLException, ParseException {

        Materia materia = new Materia();
        materia.setCodigo(request.getParameter("codigo"));
        mapearParametros(request, materia);
        dao.insertar(materia);
    }

    private void actualizarMateria(HttpServletRequest request, MateriaDAO dao)
            throws SQLException, ParseException {

        Materia materia = dao.obtenerPorCodigo(request.getParameter("codigo"));
        mapearParametros(request, materia);
        dao.actualizar(materia);
    }

    private void eliminarMateria(HttpServletRequest request, MateriaDAO dao)
            throws SQLException {

        String codigo = request.getParameter("codigo");
        dao.eliminar(codigo);
    }

    private void mapearParametros(HttpServletRequest request, Materia materia)
            throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        materia.setNombre(request.getParameter("nombre"));
        materia.setDescripcion(request.getParameter("descripcion"));
        materia.setFechaCreacion(sdf.parse(request.getParameter("fecha_creacion"))); // Nombre correcto
    }

    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect(request.getContextPath() + "/error.jsp");
    }
}
