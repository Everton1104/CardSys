package br.com.CardSys;

public class Main {

	public static void main(String[] args) throws Exception {
		//Arduino ard = new Arduino();
		MontaTela telaini = new MontaTela();
		MontaTela tela = new MontaTela();
		telaini.setVisible(true);
		//while(true) {
			//tela.cliente(ard.ler());
			tela.cliente("26120142");
			tela.setVisible(true);
		//}
	}
}
