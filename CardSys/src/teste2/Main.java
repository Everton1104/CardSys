package teste2;

public class Main {
	public static void main(String[] args)throws Exception {
		new Tela().inicio();
		while(true) {
			new Tela().showCliente(new Banco().cliente(new Arduino().ler()));
		}
	} 
}