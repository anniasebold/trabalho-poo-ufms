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
			String url = "jdbc:sqlite:/home/anniasebold/Desktop/UFMS/sqlite-tools-linux-x86-3380500/GymFit.db";
			
			this.conexao = DriverManager.getConnection(url);
			System.out.println("Conectado ao banco de dados.");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public boolean desconectar() {
		try {
			if(this.conexao.isClosed() == false) {
				this.conexao.close();
			}
			System.out.println("Desconectado do banco de dados.");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public Statement criarStatement() {
		try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
            return null;
        }
	}
	
    public PreparedStatement criarPreparedStatement(String sql, int id_gerado) {
        try {
            System.out.println("Executando SQL.");
            return conexao.prepareStatement(sql, id_gerado);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
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
            return null;
        }
    }
}
