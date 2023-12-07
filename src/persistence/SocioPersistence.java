package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.HashMap;

import model.Adherente;
import model.Club;
import model.Domicilio;
import model.Socio;
import model.TipoSocio;
import model.Titular;
import model.Vinculo;

public class SocioPersistence {
	
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public void crearSocio(Socio socio) {
    	try {
    		connection = con.conectar();
    		
            String sql = "INSERT INTO socio (nombre, apellido, dni, año_nacimiento, edad, id_club,"
            		+ " tipo, id_domicilio, fecha_afiliacion, tipo_titular, nrosociotitular, vinculo)"
            		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido() );
            preparedStatement.setInt(3, socio.getDni());
            preparedStatement.setInt(4, socio.getAñoNacimiento());
            preparedStatement.setInt(5, socio.getEdad());
            preparedStatement.setLong(6, socio.getClub().getId());
            
            if(socio instanceof Titular) {
            	Titular tit = (Titular)socio;
            	preparedStatement.setString(7, "Titular");
            	preparedStatement.setLong(8, tit.getDomicilio().getId());
            	preparedStatement.setDate(9, tit.getFechaAfiliacion());
            	preparedStatement.setString(10, tit.getTipoSocio().name());
            	
            	preparedStatement.setNull(11, java.sql.Types.INTEGER);
            	preparedStatement.setNull(12, java.sql.Types.VARCHAR);
            }else {
            	Adherente adh = (Adherente)socio;
            	preparedStatement.setString(7, "Adherente");
            	preparedStatement.setNull(8, java.sql.Types.BIGINT);
            	preparedStatement.setNull(9, java.sql.Types.DATE);
            	preparedStatement.setNull(10, java.sql.Types.VARCHAR);
            	
            	preparedStatement.setLong(11, adh.getNroSocioTitular());
            	preparedStatement.setString(12, adh.getVinculo().name());
            }
            System.out.println("PERSISTENCE:");
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    public HashMap<Long, Socio> traerSocios() {
    	try (Connection connection = con.conectar();
    	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM socio");
    	    ResultSet resultSet = preparedStatement.executeQuery()){
    		HashMap<Long, Socio>socios=new HashMap();
            while (resultSet.next()) {
            	 Long nroSocio = resultSet.getLong("nro_socio");
                 String nombre = resultSet.getString("nombre");
                 String apellido = resultSet.getString("apellido");
                 int dni = resultSet.getInt("dni");
                 int anioNacimiento = resultSet.getInt("año_nacimiento");
                 int edad = resultSet.getInt("edad");
                 Long idClub = resultSet.getLong("id_club");
                 String tipo = resultSet.getString("tipo");
                 if(tipo.equals("Titular")) {
                	 Long idDomicilio = resultSet.getLong("id_domicilio");
                	 Date fechaAfiliacion=resultSet.getDate("fecha_afiliacion");
                	 String tipoTitular=resultSet.getString("tipoTitular");
                	 java.sql.Date sqlDate = new java.sql.Date(fechaAfiliacion.getTime());
                	 Titular tit = new Titular();
                	 tit.setNroSocio(nroSocio);
                	 tit.setNombre(nombre);
                	 tit.setApellido(apellido);
                	 tit.setDni(dni);
                	 tit.setAñoNacimiento(anioNacimiento);
                	 tit.setEdad(edad);
                	 tit.setClub(buscarClub(idClub));
                	 tit.setDomicilio(buscarDomicilioId(idDomicilio));
                	 tit.setFechaAfiliacion(sqlDate);
                	 tit.setTipoSocio(stringATipoSocio(tipoTitular));
                	 
                	 socios.put(nroSocio, tit);
                 }else{
                	 Long nroSocioTit = resultSet.getLong("nrosociotitular");
                	 String vinculo = resultSet.getString("vinculo");
                	 Adherente ad = new Adherente();
                	 ad.setNroSocio(nroSocio);
                	 ad.setNombre(nombre);
                	 ad.setApellido(apellido);
                	 ad.setDni(dni);
                	 ad.setAñoNacimiento(anioNacimiento);
                	 ad.setEdad(edad);
                	 ad.setClub(buscarClub(idClub));
                	 ad.setNroSocioTitular(nroSocioTit);
                	 ad.setVinculo(stringAVinculo(vinculo));
                	 socios.put(nroSocio, ad);
                 }
               
               
            }
            return socios;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public int ultimoId() {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT MAX(nro_socio) AS max_id FROM socio;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            } else { 
                return -1; 
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
    
    public Domicilio buscarDomicilioId(Long idDomicilio) {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT * FROM domicilio WHERE id_domicilio=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, idDomicilio);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	Domicilio dom = new Domicilio();
            	dom.setId(idDomicilio);
            	String calle=resultSet.getString("calle");
            	dom.setCalle(calle);
            	int nro_calle=resultSet.getInt("nro_calle");
            	dom.setNroCalle(nro_calle);
            	int nropiso=resultSet.getInt("nropiso");
            	dom.setNroPiso(nropiso);
            	String depto=resultSet.getString("depto");
            	dom.setDepto(depto);
            	String localidad=resultSet.getString("localidad");
            	dom.setLocalidad(localidad);
                return dom;
            } else { 
            	 return null; 
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    		 return null; 
    	}
    }
    
    public Club buscarClub(Long idClub) {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT * FROM club WHERE id_club=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, idClub);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	Club cl = new Club();
            	cl.setId(idClub);
            	String domicilio=resultSet.getString("domicilio");
            	cl.setDomicilio(domicilio);
            	String ciudad=resultSet.getString("ciudad");
            	cl.setCiudad(ciudad);
            	String nombre=resultSet.getString("nombre");
            	cl.setNombre(nombre);
                return cl;
            } else { 
            	 return null; 
            }
    	}catch(SQLException e) {
    		e.printStackTrace();
    		 return null; 
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
    private void cerrarRecursos(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
