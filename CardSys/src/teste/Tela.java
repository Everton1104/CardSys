package teste;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Tela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	Banco b = new Banco();
	
	public void iniciar() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel labelInicio = new JLabel("INICIO");
		labelInicio.setFont(new Font("Tahoma", Font.PLAIN, 60));
		labelInicio.setBounds((d.width-200)/2, 0, 200, 60);
		contentPane.add(labelInicio);
		
		ArrayList<String> listaProdutos =  new ArrayList<>(b.consulta());
		
		JScrollPane Sprodutos = new JScrollPane();
		Sprodutos.setBounds(10, 100, d.width-20, d.height-150);
		JList<String> produtos = new JList<>();
		produtos.setBounds(12, 102, Sprodutos.getWidth()-2, Sprodutos.getHeight()-2);
		produtos.setFont(new Font("Tahoma", Font.PLAIN, 45));
		produtos.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getSize() {
				return listaProdutos.size();
			}

			@Override
			public String getElementAt(int index) {
				return listaProdutos.get(index);
			}
		});
		produtos.requestFocus();
		Sprodutos.add(produtos);
		contentPane.add(Sprodutos);
	}
}
