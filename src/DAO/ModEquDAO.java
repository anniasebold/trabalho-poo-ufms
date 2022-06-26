package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import model.ModEqu;

public class ModEquDAO extends Conexao {
	public boolean salvarModEqu(ModEqu modEqu) {
		conectar();
		
		String sql = "INSERT INTO mod_equ (modalidade_id, equipamento_id) VALUES (?, ?)";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		try {
			preparedStatement.setInt(1, modEqu.getIdModalidade());
			preparedStatement.setInt(2, modEqu.getIdEquipamento());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		
		return true;
	}
	
	public ArrayList<ModEqu> listarModalidadeEquipamento() {
		conectar();
		
		ArrayList<ModEqu> listaModEqu = new ArrayList<>();
		String sql = "SELECT me.id, me.modalidade_id, me.equipamento_id, m.nome, e.nome \n" + 
				"FROM mod_equ me \n" + 
				"INNER JOIN modalidades m \n" + 
				"ON me.modalidade_id = m.id \n" + 
				"INNER JOIN equipamentos e \n" + 
				"ON me.equipamento_id = e.id \n" + 
				"ORDER BY modalidade_id";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ModEqu modEqu = new ModEqu();
				modEqu.setId(resultSet.getInt(1));
				modEqu.setIdModalidade(resultSet.getInt(2));
				modEqu.setIdEquipamento(resultSet.getInt(3));
				modEqu.setNomeModalidade(resultSet.getString(4));
				modEqu.setNomeEquipamento(resultSet.getString(5));
				
				listaModEqu.add(modEqu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		
		return listaModEqu;
	}
	
	public ModEqu getModEqu(int id) {
		conectar();
		
		ModEqu modEqu = new ModEqu();
		String sql = "SELECT * FROM mod_equ WHERE id = '"+ id +"'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				modEqu.setId(resultSet.getInt("id"));
				modEqu.setIdModalidade(resultSet.getInt("modalidade_id"));
				modEqu.setIdEquipamento(resultSet.getInt("equipamento_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		
		return modEqu;
	}
	
	public boolean editarModEqu(ModEqu modEqu) {
		conectar();

		String sql = "UPDATE mod_equ SET modalidade_id = '" + modEqu.getIdModalidade() + "', equipamento_id = '"
				+ modEqu.getIdEquipamento() + "' WHERE id = '" + modEqu.getId() + "'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		
		return true;
	}
	
	public boolean removerModEqu(int id) {
		conectar();
		
		String sql = "DELETE from mod_equ WHERE id = '"+ id +"'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		desconectar();
		
		return true;
	}
}
