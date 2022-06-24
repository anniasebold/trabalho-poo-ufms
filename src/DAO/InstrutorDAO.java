package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import model.Instrutor;

public class InstrutorDAO extends Conexao {

	public boolean salvarInstrutor(Instrutor instrutor) {
		conectar();

		String sql = "INSERT INTO instrutores (nome, formacao) VALUES (?, ?);";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);

		try {
			preparedStatement.setString(1, instrutor.getNome());
			preparedStatement.setString(2, instrutor.getFormacao());
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

	public ArrayList<Instrutor> listarInstrutores() {
		ArrayList<Instrutor> listaInstrutores = new ArrayList<>();

		conectar();

		String sql = "SELECT * FROM instrutores;";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);

		try {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Instrutor instrutor = new Instrutor(resultSet.getInt("id"), resultSet.getString("nome"),
						resultSet.getString("formacao"));
				listaInstrutores.add(instrutor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();

		return listaInstrutores;
	}

	public Instrutor getInstrutor(int id) {
		conectar();

		Instrutor instrutor = new Instrutor();
		String sql = "SELECT * FROM instrutores WHERE id = '" + id + "'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);

		try {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				instrutor.setId(resultSet.getInt("id"));
				instrutor.setNome(resultSet.getString("nome"));
				instrutor.setFormacao(resultSet.getString("formacao"));
				
				desconectar();
				
				return instrutor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();

		return null;
	}

	public boolean removerInstrutor(int id) {
		conectar();

		String sql = "DELETE FROM instrutores WHERE id = '" + id + "'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);

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

	public boolean editarInstrutor(Instrutor instrutor) {
		conectar();

		String sql = "UPDATE instrutores SET nome = '" + instrutor.getNome() + "', formacao = '"
				+ instrutor.getFormacao() + "' WHERE id = '" + instrutor.getId() + "'";
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
}
