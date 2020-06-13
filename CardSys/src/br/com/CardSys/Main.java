package br.com.CardSys;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		Arduino ard = new Arduino();
		MontaTela tela = new MontaTela();
		tela.setVisible(true);
		tela.cliente(ard.ler());
		tela.setVisible(true);
		

	}

}
