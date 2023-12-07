package model;

public class Adherente extends Socio {
	private Long nroSocioTitular;
	private Vinculo vinculo;
	
	public Adherente(Long nroSocio, String nombre, String apellido, int dni, int edad, int añoNacimiento, Club club,
			Long nroSocioTitular, Vinculo vinculo) {
		super(nroSocio, nombre, apellido, dni, edad, añoNacimiento, club);
		this.nroSocioTitular = nroSocioTitular;
		this.vinculo = vinculo;
	}

	public Adherente() {
		super();
	}

	public Long getNroSocioTitular() {
		return nroSocioTitular;
	}

	public void setNroSocioTitular(Long nroSocioTitular) {
		this.nroSocioTitular = nroSocioTitular;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}
	

	
}
