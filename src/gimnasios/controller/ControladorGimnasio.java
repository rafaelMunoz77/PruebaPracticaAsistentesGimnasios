package gimnasios.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gimnasios.model.Gimnasio;



public class ControladorGimnasio {
	
	private static String nombreTabla = "gimnasio";

	
	public static List<Gimnasio> findAll() {
		List<Gimnasio> l = new ArrayList<Gimnasio>();
		
		try {
			ResultSet rs = ConnectionManager.getConexion().createStatement()
					.executeQuery("Select * from " + nombreTabla);
			while (rs.next()) {
				Gimnasio c = getEntidadFromResultSet(rs);
				l.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;		
	}
	
	
	
	
	
	private static Gimnasio getEntidadFromResultSet (ResultSet rs) throws SQLException {
		Gimnasio o = new Gimnasio();
		o.setId(rs.getInt("id"));
		o.setDescripcion(rs.getString("descripcion"));
		return o;
	}
}
