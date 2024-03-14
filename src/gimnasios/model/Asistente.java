package gimnasios.model;

import java.util.Date;

public class Asistente {
	private int id;
	private int idGimnasio;
	private String dniNiePasaporte;
	private int idLocalidad;
	private boolean activo;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private float cuotaMensual;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdGimnasio() {
		return idGimnasio;
	}
	public void setIdGimnasio(int idGimnasio) {
		this.idGimnasio = idGimnasio;
	}
	public String getDniNiePasaporte() {
		return dniNiePasaporte;
	}
	public void setDniNiePasaporte(String dniNiePasaporte) {
		this.dniNiePasaporte = dniNiePasaporte;
	}
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public float getCuotaMensual() {
		return cuotaMensual;
	}
	public void setCuotaMensual(float cuotaMensual) {
		this.cuotaMensual = cuotaMensual;
	}
	
	@Override
	public String toString() {
		return nombre + " " + apellidos;
	}
}
