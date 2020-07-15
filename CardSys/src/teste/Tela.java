package teste;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	Banco b = new Banco();
	
	public void produtos() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel labelInicio = new JLabel("PRODUTOS");
		labelInicio.setFont(new Font("Tahoma", Font.PLAIN, 60));
		labelInicio.setBounds((d.width-350)/2, 0, 350, 60);
		contentPane.add(labelInicio);
		
		JTextField busca =  new JTextField();
		busca.setBounds((d.width-(d.width-10))/2, 65, d.width-10, 50);
		busca.setFont(new Font("Tahoma", Font.PLAIN, 45));
		busca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE && k!=KeyEvent.VK_ENTER) {
					try {
						//testar lista.setvisible true para mostrar e tbm tentar usar o jdialog com jtextfield
					}catch(Exception err) {
						err.printStackTrace();
					}
				}else if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE && k==KeyEvent.VK_ENTER) {
					
				}
			};
		});
		contentPane.add(busca);
	
		ArrayList<String> listaProdutos =  new ArrayList<>();
		ScrollPane Sprodutos = new ScrollPane();
		Sprodutos.setBounds(5, 150, d.width-10, d.height-150);
		JList<String> produtos = new JList<>();
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
		produtos.setBounds(5, 150, d.width-10, d.height-150);
		produtos.setFont(new Font("Tahoma", Font.PLAIN, 45));
		Sprodutos.add(produtos);
		contentPane.add(Sprodutos);
	}
}
