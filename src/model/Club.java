package model;

import java.util.HashMap;

public class Club {
	private Long id;
	private String nombre;
	private String domicilio;
	private String ciudad;
	private HashMap<Long, Socio>socios;
	
	public Club(Long id, String nombre, String domicilio, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.socios = new HashMap<Long, Socio>();
	}

	public Club() {
		this.socios=new HashMap<Long, Socio>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public HashMap<Long, Socio> getSocios() {
		return socios;
	}

	public void setSocios(HashMap<Long, Socio> socios) {
		this.socios = socios;
	}
	
	
}
