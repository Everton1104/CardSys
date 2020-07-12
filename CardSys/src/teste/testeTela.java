package teste;

import java.sql.SQLException;

public class testeTela {

	public static void main(String[] args) {
		try {
			Tela t = new Tela();
			t.produtos();
			t.setVisible(true);
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
