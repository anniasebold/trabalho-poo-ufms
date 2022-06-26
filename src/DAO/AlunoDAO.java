package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Conexao;
import model.Aluno;


public class AlunoDAO extends Conexao {
	public boolean salvarAluno(Aluno aluno) {
		conectar();
		
		String sql = "INSERT INTO alunos (nome, idade, instrutor_id, modalidade_id) VALUES  (?, ?, ?, ?)";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		try {
			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setInt(2, aluno.getIdade());
			preparedStatement.setInt(3, aluno.getIdInstrutor());
			preparedStatement.setInt(4, aluno.getIdModalidade());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		desconectar();
		
		return true;
	}
	
	public ArrayList<Aluno> listarAlunos() {
		conectar();
		
		ArrayList<Aluno> listaAlunos = new ArrayList<>();
		String sql = "SELECT a.id, a.nome, a.idade, a.instrutor_id, a.modalidade_id, i.nome, m.nome \n" + 
				"FROM alunos a \n" + 
				"INNER JOIN instrutores i \n" + 
				"ON i.id = a.instrutor_id \n" + 
				"INNER JOIN modalidades m \n" + 
				"on m.id = a.modalidade_id";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(resultSet.getInt(1));
				aluno.setNome(resultSet.getString(2));
				aluno.setIdade(resultSet.getInt(3));
				aluno.setIdInstrutor(resultSet.getInt(4));
				aluno.setIdModalidade(resultSet.getInt(5));
				aluno.setNomeInstrutor(resultSet.getString(6));
				aluno.setNomeModalidade(resultSet.getString(7));
				
				listaAlunos.add(aluno);
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
		
		return listaAlunos;
	}
	
	public Aluno getAluno(int id) {
		conectar();
		
		String sql = "SELECT * FROM alunos WHERE id = '"+ id +"'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(resultSet.getInt("id"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setIdade(resultSet.getInt("idade"));
				aluno.setIdInstrutor(resultSet.getInt("instrutor_id"));
				aluno.setIdModalidade(resultSet.getInt("modalidade_id"));
				
				desconectar();
				
				return aluno;
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
		
		return null;
	}
	
	public boolean editarAluno(Aluno aluno) {
		conectar();

		String sql = "UPDATE alunos SET nome = '" + aluno.getNome() + "', idade = '" + aluno.getIdade()
				+ "', instrutor_id = '" + aluno.getIdInstrutor() + "', modalidade_id = '" + aluno.getIdModalidade()
				+ "' WHERE id = '"+ aluno.getId() +"'";
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
	
	public boolean removerAluno(int id) {
		conectar();
		
		String sql = "DELETE from alunos WHERE id = '"+id+"'";
		PreparedStatement preparedStatement = criarPreparedStatement(sql);
		
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
}
