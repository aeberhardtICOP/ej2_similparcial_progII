package service;

import java.util.HashMap;

import model.Club;
import persistence.ClubPersistence;

public class ClubService {
	private ClubPersistence clper;
	private HashMap<Long, Club>clubes;
	
	public ClubService() {
		clper = new ClubPersistence();
		clubes = new HashMap<Long, Club>();
	}
	
	public void crearClub(String nombre, String domicilio, String ciudad) {
		Club cl = new Club();
		cl.setId((long) (clper.ultimoId()+1));
		cl.setCiudad(ciudad);
		cl.setDomicilio(domicilio);
		cl.setNombre(nombre);
		clper.crearClub(cl);
		clubes.put((long) (clper.ultimoId()+1), cl);
		
	}
	
	public void clubesAMemoria() {
		this.clubes=clper.traerClubes();
	}
	
	public HashMap<Long,Club> getClubes(){
		return this.clubes;
	}
	
}
