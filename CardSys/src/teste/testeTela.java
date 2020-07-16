package teste;

import java.sql.SQLException;

public class testeTela {

	public static void main(String[] args) {
		try {
			Tela t = new Tela();
			t.cliente("2630030");
			t.setVisible(true);
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
