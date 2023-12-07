package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Club;
import model.Domicilio;

public class DomicilioPersistence {
	Conexion con = new Conexion();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public void crearDomicilio(Domicilio dom) {
    	try {
    		connection = con.conectar();
    		
            String sql = "INSERT INTO domicilio (calle, nropiso, depto, localidad, nro_calle)"
            		+ "VALUES(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dom.getCalle());
            preparedStatement.setInt(2, dom.getNroPiso());
            preparedStatement.setString(3,dom.getDepto());
            preparedStatement.setString(4, dom.getLocalidad());
            preparedStatement.setInt(5, dom.getNroCalle());
            preparedStatement.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		cerrarRecursos(connection, preparedStatement, resultSet);
    	}
    }
    
    public int ultimoId() {
    	try {
    		connection = con.conectar();
    		
            String sql = "SELECT MAX(id_domicilio) AS max_id FROM domicilio;";
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
