package model;

public class Domicilio {
	private Long id;
	private String calle;
	private int nroPiso;
	private String depto;
	private String localidad;
	private int nroCalle;
	
	public Domicilio(Long id, String calle, int nroPiso, String depto, String localidad, int nroCalle) {
		super();
		this.id = id;
		this.calle = calle;
		this.nroPiso = nroPiso;
		this.depto = depto;
		this.localidad = localidad;
		this.nroCalle=nroCalle;
	}

	public Domicilio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public int getNroCalle() {
		return nroCalle;
	}

	public void setNroCalle(int nroCalle) {
		this.nroCalle = nroCalle;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNroPiso() {
		return nroPiso;
	}

	public void setNroPiso(int nroPiso) {
		this.nroPiso = nroPiso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	
}
