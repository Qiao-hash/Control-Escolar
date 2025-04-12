package sv.edu.udb.desafiopractico2.dao;

import sv.edu.udb.desafiopractico2.model.Inscripcion;
import sv.edu.udb.desafiopractico2.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InscripcionDAO {

    public void insertar(Inscripcion inscripcion) throws SQLException {
        String sql = "INSERT INTO Estudiante_Materias (carnet_alumno, codigo_materia) VALUES (?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, inscripcion.getCarnetAlumno());
            pstmt.setString(2, inscripcion.getCodigoMateria());
            pstmt.executeUpdate();
        }
    }

    // Eliminar inscripción
    public void eliminar(String carnet, String codigoMateria) throws SQLException {
        String sql = "DELETE FROM Estudiante_Materias WHERE carnet_alumno = ? AND codigo_materia = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carnet);
            pstmt.setString(2, codigoMateria);
            pstmt.executeUpdate();
        }
    }

    // Conteo de estudiantes por materia
    public Map<String, Integer> contarEstudiantesPorMateria() throws SQLException {
        Map<String, Integer> conteo = new HashMap<>();
        String sql = "SELECT codigo_materia, COUNT(*) AS total FROM Estudiante_Materias GROUP BY codigo_materia";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                conteo.put(rs.getString("codigo_materia"), rs.getInt("total"));
            }
        }
        return conteo;
    }

    // Lista de estudiantes en una materia
    public List<String> listarEstudiantesPorMateria(String codigoMateria) throws SQLException {
        List<String> estudiantes = new ArrayList<>();
        String sql = "SELECT a.nombre, a.apellidos FROM Estudiante_Materias em " +
                "JOIN Alumnos a ON em.carnet_alumno = a.carnet " +
                "WHERE em.codigo_materia = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigoMateria);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(rs.getString("nombre") + " " + rs.getString("apellidos"));
                }
            }
        }
        return estudiantes;
    }

    // Validar inscripción existente
    public boolean existeInscripcion(String carnet, String codigoMateria) throws SQLException {
        String sql = "SELECT 1 FROM Estudiante_Materias WHERE carnet_alumno = ? AND codigo_materia = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carnet);
            pstmt.setString(2, codigoMateria);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Map<String, String>> listarTodasInscripciones() throws SQLException {
        List<Map<String, String>> inscripciones = new ArrayList<>();
        String sql = "SELECT a.carnet, a.nombre AS nombre_alumno, a.apellidos, m.codigo AS codigo_materia, m.nombre AS nombre_materia " +
                "FROM Estudiante_Materias em " +
                "JOIN Alumnos a ON em.carnet_alumno = a.carnet " +
                "JOIN Materias m ON em.codigo_materia = m.codigo";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, String> inscripcion = new HashMap<>();
                inscripcion.put("carnet", rs.getString("carnet"));
                inscripcion.put("nombreAlumno", rs.getString("nombre_alumno") + " " + rs.getString("apellidos"));
                inscripcion.put("codigoMateria", rs.getString("codigo_materia"));
                inscripcion.put("nombreMateria", rs.getString("nombre_materia"));
                inscripciones.add(inscripcion);
            }
        }
        return inscripciones;
    }
}