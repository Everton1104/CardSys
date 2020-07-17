package teste;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Tela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	Banco b = new Banco();
	ArrayList<String> produtos = new ArrayList<>();
	
	public void cliente(String numero_id) throws SQLException {
		
		Cliente c = new Cliente();
		c = b.execute(numero_id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel id_cliente = new JLabel("ID: "+c.getId());
		id_cliente.setBounds(50, 50, 300, 40);
		id_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(id_cliente);
		
		JLabel nome_cliente = new JLabel("Nome: "+c.getNome());
		nome_cliente.setBounds(50, 100, 300, 40);
		nome_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(nome_cliente);
		
		JLabel telefone_cliente = new JLabel("Telefone: "+c.getTelefone());
		telefone_cliente.setBounds(50, 150, 600, 40);
		telefone_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(telefone_cliente);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 200, d.width-20, d.height-200);
		produtos = c.getProdutos();
		JList<String> lista =  new JList<>(new AbstractListModel<>() {
			private static final long serialVersionUID = 1L;
			@Override
			public int getSize() {
				return produtos.size();
			}
			@Override
			public String getElementAt(int index) {
				return produtos.get(index);
			}
		});
		lista.setBounds(10, 200, d.width-20, d.height-200);
		lista.setFont(new Font("Tahoma", Font.PLAIN, 45));
		scroll.add(lista);
		contentPane.add(scroll);
		
		JButton add = new JButton("Adicionar produto");
		add.setFont(new Font("Tahoma", Font.PLAIN, 45));
		add.setBounds(d.width-900, 120, 400, 70);
		add.requestFocus();
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {	
					Tela t = new Tela();
					t.produtos(numero_id);
					t.setVisible(true);
				}catch(Exception addErr) {addErr.printStackTrace();}
			}
		});
		contentPane.add(add);
		
		JButton edt = new JButton("Editar");
		edt.setFont(new Font("Tahoma", Font.PLAIN, 45));
		edt.setBounds(d.width-450, 120, 400, 70);
		contentPane.add(edt);
	}
	
	public void produtos(String numero_id) throws SQLException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel labelInicio = new JLabel("PRODUTOS");
		labelInicio.setFont(new Font("Tahoma", Font.PLAIN, 60));
		labelInicio.setBounds((d.width-350)/2, 0, 350, 60);
		contentPane.add(labelInicio);
		
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds((d.width-(d.width-10))/2, 265, d.width-10, d.height-10);
		JList<String> lista =  new JList<>();
		lista.setBounds((d.width-(d.width-10))/2, 265, d.width-10, d.height-10);
		lista.setFont(new Font("Tahoma", Font.PLAIN, 45));
		scroll.add(lista);
		contentPane.add(scroll);
		
		JTextField busca =  new JTextField();
		busca.setBounds((d.width-(d.width-10))/2, 65, d.width-10, 50);
		busca.setFont(new Font("Tahoma", Font.PLAIN, 45));
		busca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE && k!=KeyEvent.VK_ENTER) {
					try {
						ArrayList<String> produtos =  new ArrayList<>(b.consulta(busca.getText()));
						AbstractListModel<String> modelo = new AbstractListModel<>() {
							private static final long serialVersionUID = 1L;
							@Override
							public int getSize() {
								return produtos.size();
							}
								@Override
							public String getElementAt(int index) {
								return produtos.get(index);
							}
						};
						lista.setModel(modelo);
						lista.setSelectedIndex(0);
					}catch(Exception err) {
						err.printStackTrace();
					}
				}else if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE && k==KeyEvent.VK_ENTER) {
					String qtde = JOptionPane.showInputDialog("Quantidade de "+lista.getSelectedValue());
					try {
						b.add(lista.getSelectedValue(), numero_id, qtde);
					}catch(Exception buscaErr) {buscaErr.printStackTrace();}
				}
			};
		});
		contentPane.add(busca);
	}
}
