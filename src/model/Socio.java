package model;

public abstract class Socio {
	private Long nroSocio;
	private String nombre;
	private String apellido;
	private int dni;
	private int edad;
	private int añoNacimiento;
	private Club club;
	
	public Socio(Long nroSocio, String nombre, String apellido, int dni, int edad, int añoNacimiento, Club club) {
		this.nroSocio = nroSocio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
		this.añoNacimiento = añoNacimiento;
		this.club = club;
	}

	public Socio() {

	}

	public Long getNroSocio() {
		return nroSocio;
	}

	public void setNroSocio(Long nroSocio) {
		this.nroSocio = nroSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(int añoNacimiento) {
		this.añoNacimiento = añoNacimiento;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
}
