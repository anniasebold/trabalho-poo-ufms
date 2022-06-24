package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
}
