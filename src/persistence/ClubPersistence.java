package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Adherente;
import model.Club;
import model.Socio;
import model.Titular;

public class ClubPersistence {
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public void crearClub(Club cl) {
    	try {
    		connection = con.conectar();
    		
            String sql = "INSERT INTO club (domicilio, ciudad, nombre)"
            		+ "VALUES(?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cl.getDomicilio());
            preparedStatement.setString(2, cl.getCiudad());
            preparedStatement.setString(3, cl.getNombre());
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
    public HashMap<Long, Club> traerClubes() {
    	try {
    		HashMap<Long, Club>clubes=new HashMap();
    		connection = con.conectar();
            String sql = "SELECT * FROM club;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	 Long idClub=resultSet.getLong("id_club");
            	 String domicilio = resultSet.getString("domicilio");
            	 String ciudad = resultSet.getString("ciudad");
            	 String nombre = resultSet.getString("nombre");
            	 Club cl = new Club();
            	 cl.setId(idClub);
            	 cl.setNombre(nombre);
            	 cl.setCiudad(ciudad);
            	 cl.setDomicilio(domicilio);
            	 clubes.put(idClub, cl);
               
            }
            return clubes;
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    public int ultimoId() {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT MAX(id_club) AS max_id FROM club;";
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
	
}
