package service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Adherente;
import model.Club;
import model.Domicilio;
import model.Socio;
import model.TipoSocio;
import model.Titular;
import model.Vinculo;
import persistence.DomicilioPersistence;
import persistence.SocioPersistence;

public class SocioService {
	private SocioPersistence socpers;
	private DomicilioPersistence dompers;
	private HashMap<Long, Socio>socios;
	private HashMap<Long, Domicilio>domicilios;
	
	public SocioService() {
		this.socpers= new SocioPersistence();
		this.socios = new HashMap<Long, Socio>();
		this.dompers = new DomicilioPersistence();
		this.domicilios = new HashMap<Long, Domicilio>();
		
	}
	
	public void crearSocio(String nombre, String apellido, String dni, String añoNacimiento, String edad, Club club, String tipo, Domicilio dom,
			String fechaAfiliacion, String tipoTitular, String nroSocioTitular, String vinculo) {

		if(tipo.equals("Adherente")) {
			Adherente ad = new Adherente();
			ad.setNombre(nombre);
			ad.setApellido(apellido);
			ad.setClub(club);
			ad.setDni(Integer.parseInt(dni));
			ad.setEdad(Integer.parseInt(edad));
			ad.setAñoNacimiento(Integer.parseInt(añoNacimiento));
			ad.setNroSocioTitular((long) Integer.parseInt(nroSocioTitular));
			ad.setVinculo(stringAVinculo(vinculo));
			System.out.println("SERVICE ADHERENTE");
			socpers.crearSocio(ad);
			this.socios.put((long)socpers.ultimoId()+1, ad);
			
		}else if(tipo.equals("Titular")) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Titular tit = new Titular();
			
			dom.setId((long) (dompers.ultimoId()+1));
			dompers.crearDomicilio(dom);
			domicilios.put((long) (dompers.ultimoId()+1), dom);
			
			tit.setNombre(nombre);
			tit.setApellido(apellido);
			tit.setClub(club);
			tit.setDni(Integer.parseInt(dni));
			tit.setEdad(Integer.parseInt(edad));
			tit.setAñoNacimiento(Integer.parseInt(añoNacimiento));
			tit.setDomicilio(dom);
			try {
				tit.setFechaAfiliacion(new java.sql.Date(formato.parse(fechaAfiliacion).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				tit.setFechaAfiliacion(null);
			}
			tit.setTipoSocio(stringATipoSocio(tipoTitular));
			System.out.println("SERVICE T");
			socpers.crearSocio(tit);
			this.socios.put((long)socpers.ultimoId()+1, tit);
		}
		

	}
	
	public void sociosAMemoria() {
		this.socios=socpers.traerSocios();
		for (Entry<Long, Socio> entry : this.socios.entrySet()) {
			if(entry.getValue() instanceof Titular) {
				Titular t = (Titular) entry.getValue();
				this.domicilios.put(t.getDomicilio().getId(), t.getDomicilio());
			}
		}
	}
	
	public HashMap<Long, Socio> getSocios(){
		return this.socios;
	}
	
	 public TipoSocio stringATipoSocio(String tiposocio) {
	    	if(tiposocio.equals("AFICIONADO")) {
	    		return TipoSocio.AFICIONADO;
	    	}else if(tiposocio.equals("AMATEUR")) {
	    		return TipoSocio.AMATEUR;
	    	}else if(tiposocio.equals("RECREATIVO")){
	    		return TipoSocio.RECREATIVO;
	    	}
	    	return null; 
	    }
	    public Vinculo stringAVinculo(String vinculo) {
	    	if(vinculo.equals("DIRECTO")) {
	    		return Vinculo.DIRECTO;
	    	}else if(vinculo.equals("INDIRECTO")) {
	    		return Vinculo.INDIRECTO;
	    	}else {
	    		return null; 
	    	}
	    }
}
