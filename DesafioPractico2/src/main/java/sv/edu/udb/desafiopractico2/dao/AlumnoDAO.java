package sv.edu.udb.desafiopractico2.dao;

import sv.edu.udb.desafiopractico2.model.Alumno;
import sv.edu.udb.desafiopractico2.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    // Listar todos los alumnos
    public List<Alumno> listar() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setCarnet(rs.getString("carnet"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidos(rs.getString("apellidos"));
                a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                a.setDireccion(rs.getString("direccion"));
                alumnos.add(a);
            }
        }
        return alumnos;
    }

    // Insertar nuevo alumno
    public void insertar(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumnos (carnet, nombre, apellidos, fecha_nacimiento, direccion) VALUES (?, ?, ?, ?, ?)";
        System.out.println("[DEBUG] Query: " + sql);

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, alumno.getCarnet());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellidos());
            pstmt.setDate(4, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            pstmt.setString(5, alumno.getDireccion());

            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("[DEBUG] Filas afectadas: " + filasAfectadas);
        }
        }
    public void actualizar(Alumno alumno) throws SQLException {
        String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, direccion = ? WHERE carnet = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellidos());
            pstmt.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getCarnet());
            pstmt.executeUpdate();
        }
    }

    // Eliminar alumno por carnet
    public void eliminar(String carnet) throws SQLException {
        String sql = "DELETE FROM alumnos WHERE carnet = ?"; // Tabla y columna correctas

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carnet);
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("[DEBUG] Filas eliminadas: " + filasAfectadas);
        }
    }

    // Obtener alumno específico por carnet (para edición)
    public Alumno obtenerPorCarnet(String carnet) throws SQLException {
        String sql = "SELECT * FROM alumnos WHERE carnet = ?";
        Alumno alumno = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carnet);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setCarnet(rs.getString("carnet"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellidos(rs.getString("apellidos"));
                    alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    alumno.setDireccion(rs.getString("direccion"));
                }
            }
        }
        return alumno;
    }
}