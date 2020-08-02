package teste2;

public class Main {

	public static void main(String[] args) {
		try {
			new Tela().showCliente(new Banco().cliente("4"));
		} 
		catch (Exception e) {e.printStackTrace();}
	}
}
