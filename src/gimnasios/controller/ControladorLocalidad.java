package gimnasios.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gimnasios.model.Localidad;




public class ControladorLocalidad {
	
	private static String nombreTabla = "localidad";

	
	public static List<Localidad> findAll() {
		List<Localidad> l = new ArrayList<Localidad>();
		
		try {
			ResultSet rs = ConnectionManager.getConexion().createStatement()
					.executeQuery("Select * from " + nombreTabla);
			while (rs.next()) {
				Localidad c = getEntidadFromResultSet(rs);
				l.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;		
	}
	
	
	
	
	
	private static Localidad getEntidadFromResultSet (ResultSet rs) throws SQLException {
		Localidad o = new Localidad();
		o.setId(rs.getInt("id"));
		o.setLocalidad(rs.getString("localidad"));
		return o;
	}
}
