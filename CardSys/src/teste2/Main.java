package teste2;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		try {
			//while(true) {
				new Tela().showCliente(new Banco().cliente(JOptionPane.showInputDialog("id")));
			//}
		} 
		catch (Exception e) {Main.main(null);}
	}
}

