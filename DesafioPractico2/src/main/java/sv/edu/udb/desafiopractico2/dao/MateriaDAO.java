package sv.edu.udb.desafiopractico2.dao;
import sv.edu.udb.desafiopractico2.model.Materia;
import sv.edu.udb.desafiopractico2.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public List<Materia> listar() throws SQLException{
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materias";

        try (Connection con = DatabaseUtil.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Materia m = new Materia();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setFechaCreacion(rs.getDate("fecha_creacion"));
                materias.add(m);
            }
        }
        return materias;
    }

    public void insertar(Materia materia) throws SQLException {
        String sql = "INSERT INTO materias (codigo, nombre, descripcion, fecha_creacion) VALUES (?, ?, ?, ?)";
        System.out.println("[DEBUG] Ejecutando inserción: " + sql);

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, materia.getCodigo());
            stmt.setString(2, materia.getNombre());
            stmt.setString(3, materia.getDescripcion());
            stmt.setDate(4, new java.sql.Date(materia.getFechaCreacion().getTime()));

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("[DEBUG] Filas afectadas: " + filasAfectadas); // Debe ser 1
        } catch (SQLException e) {
            System.err.println("[ERROR] Fallo al insertar materia: " + e.getMessage());
            throw e;
        }
    }

    public void actualizar(Materia materia) throws SQLException{
        String sql = "UPDATE materias SET nombre=?,descripcion=?,fecha_creacion=? WHERE codigo=?";
        try (Connection con = DatabaseUtil.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, materia.getNombre());
            stmt.setString(2, materia.getDescripcion());
            stmt.setDate(3, new java.sql.Date(materia.getFechaCreacion().getTime()));
            stmt.setString(4, materia.getCodigo());

            stmt.executeUpdate();
        }
    }

    public void eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM materias WHERE codigo=?";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }
    public Materia obtenerPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM Materias WHERE codigo=?";
        Materia materia = null;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    materia = new Materia();
                    materia.setCodigo(rs.getString("codigo"));
                    materia.setNombre(rs.getString("nombre"));
                    materia.setDescripcion(rs.getString("descripcion"));
                    materia.setFechaCreacion(rs.getDate("fecha_creacion"));
                }
            }
        }
        return materia;
    }

}
