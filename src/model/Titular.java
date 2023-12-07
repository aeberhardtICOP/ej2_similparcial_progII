package model;

import java.sql.Date;

public class Titular extends Socio {
	private Domicilio domicilio;
	private Date fechaAfiliacion;
	private TipoSocio tipoSocio;
	
	public Titular(Long nroSocio, String nombre, String apellido, int dni, int edad, int añoNacimiento, Club club,
			Domicilio domicilio, Date fechaAfiliacion, TipoSocio tipoSocio) {
		super(nroSocio, nombre, apellido, dni, edad, añoNacimiento, club);
		this.domicilio = domicilio;
		this.fechaAfiliacion = fechaAfiliacion;
		this.tipoSocio = tipoSocio;
	}


	public Titular() {
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(Date fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public TipoSocio getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(TipoSocio tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

}
