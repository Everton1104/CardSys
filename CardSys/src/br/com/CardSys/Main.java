package br.com.CardSys;

public class Main {

	public static void main(String[] args) throws Exception {
		Arduino ard = new Arduino();
		MontaTela tela = new MontaTela();
		tela.setVisible(true);
		tela.cliente(ard.ler());
		tela.setVisible(true);
	}

}
