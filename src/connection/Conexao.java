package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection conexao;
	
	public boolean conectar() {
		
		try {
			String url = "jdbc:sqlite:/home/anniasebold/Desktop/UFMS/TRABALHO-PRATICO/GymFit/database/GymFit.db";
			
			this.conexao = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	public boolean desconectar() {
		try {
			if(this.conexao.isClosed() == false) {
				this.conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Statement criarStatement() {
		try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        }
	}
	
    public PreparedStatement criarPreparedStatement(String sql, int id_gerado) {
        try {
            return conexao.prepareStatement(sql, id_gerado);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public Connection getConexao() {
        return this.conexao;
    }
	
	public PreparedStatement criarPreparedStatement(String sql) {
        try {
            return this.conexao.prepareStatement(sql);
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        }
    }
}
