package br.com.CardSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Banco {
	public void execute(String option, String dados) throws SQLException {

		String url = "jdbc:mysql://127.0.0.1:3306/banco_cardsys";
		String user = "root";
		String password = "root";

		Connection conexao = DriverManager.getConnection(url, user, password);
		
		String sql = "INSERT INTO loja (nome,sobrenome,idade,salario) VALUES ('Mario','Corleone','28','2322.39')";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.execute();
	}
}
