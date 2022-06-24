package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import model.Equipamento;

public class EquipamentoDAO extends Conexao {
	public boolean salvarEquipamento(Equipamento equipamento) {
		conectar();

		String sql = "INSERT INTO equipamentos (nome, musc_trabalhado, estado) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);

		try {
			preparedStatement.setString(1, equipamento.getNome());
			preparedStatement.setString(2, equipamento.getMusculoTrab());
			preparedStatement.setString(3, equipamento.getEstado());
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

	public ArrayList<Equipamento> listarEquipamentos() {
		ArrayList<Equipamento> listaEquipamentos = new ArrayList<>();

		conectar();

		String sql = "SELECT * FROM equipamentos";

		PreparedStatement preparedStatement = criarPreparedStatement(sql);

		try {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Equipamento equipamento = new Equipamento(resultSet.getInt("id"), resultSet.getString("nome"),
						resultSet.getString("musc_trabalhado"), resultSet.getString("estado"));
				listaEquipamentos.add(equipamento);
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

		return listaEquipamentos;

	}

	public Equipamento getEquipamento(int id) {
		conectar();

		Equipamento equipamento = new Equipamento();
		String sql = "SELECT * FROM equipamentos where id = '" + id + "'";

		PreparedStatement preparedStatement = criarPreparedStatement(sql);

		try {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				equipamento.setId(resultSet.getInt("id"));
				equipamento.setNome(resultSet.getString("nome"));
				equipamento.setMusculoTrab(resultSet.getString("musc_trabalhado"));
				equipamento.setEstado(resultSet.getString("estado"));
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

		return equipamento;
	}

	public boolean editarEquipamento(Equipamento equipamento) {
		conectar();

		String sql = "UPDATE equipamentos SET nome = '" + equipamento.getNome() + "', musc_trabalhado = '"
				+ equipamento.getMusculoTrab() + "', estado = '" + equipamento.getEstado() + "' WHERE id = '"
				+ equipamento.getId() + "'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);

		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();

		return true;
	}

	public boolean removerEquipamento(int id) {
		conectar();

		String sql = "DELETE FROM equipamentos WHERE id = '" + id + "'";
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

}
