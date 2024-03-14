package gimnasios.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gimnasios.model.Asistente;
import gimnasios.model.Localidad;




public class ControladorAsistente {
	
	private static String nombreTabla = "asistente";

	
	/**
	 * 
	 * @param idGimnasio
	 * @param apellidosFilter
	 * @return
	 */
	public static List<Asistente> filterByApellidosAndIdGimnasio(int idGimnasio, String apellidosFilter) {
		List<Asistente> l = new ArrayList<Asistente>();
		
		try {
			PreparedStatement ps = ConnectionManager.getConexion().prepareStatement(
					"select * from " + nombreTabla + " where idGimnasio = ? and apellidos like ?");
			ps.setInt(1, idGimnasio);
			ps.setString(2, "%" + apellidosFilter + "%");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Asistente c = getEntidadFromResultSet(rs);
				l.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;		
	}
	
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Asistente getEntidadFromResultSet (ResultSet rs) throws SQLException {
		Asistente a = new Asistente();
		a.setId(rs.getInt("id"));
		a.setActivo(rs.getBoolean("activo"));
		a.setApellidos(rs.getString("apellidos"));
		a.setCuotaMensual(rs.getFloat("cuotaMensual"));
		a.setDniNiePasaporte(rs.getString("dniNiePasaporte"));
		a.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		a.setIdGimnasio(rs.getInt("idGimnasio"));
		a.setIdLocalidad(rs.getInt("idLocalidad"));
		a.setNombre(rs.getString("Nombre"));
		
		return a;
	}
	
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public static int update (Asistente a) {
		try {
			PreparedStatement ps = ConnectionManager.getConexion().prepareStatement(
					"update " + nombreTabla + " set dniNiePasaporte = ?, idLocalidad = ?, activo = ?, "
							+ "nombre = ?, apellidos = ?, fechaNacimiento = ?, cuotaMensual = ? "
							+ "where id = ?");
			ps.setString(1, a.getDniNiePasaporte());
			ps.setInt(2, a.getIdLocalidad());
			ps.setBoolean(3, a.isActivo());
			ps.setString(4, a.getNombre());
			ps.setString(5, a.getApellidos());
			
			if (a.getFechaNacimiento() != null) {
				ps.setDate(6, new java.sql.Date(a.getFechaNacimiento().getTime()));
			}
			else {
				ps.setDate(6, null);
			}
			
			ps.setFloat(7, a.getCuotaMensual());
			ps.setInt(8, a.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;		
	}
}
