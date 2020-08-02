package teste2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Tela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	public void showCliente(Cliente cliente){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//ID
		JLabel id_cliente_lbl = new JLabel("ID: ");
		id_cliente_lbl.setBounds(50, 50, 250, 50);
		id_cliente_lbl.setHorizontalAlignment(JLabel.RIGHT);
		id_cliente_lbl.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(id_cliente_lbl);
		
		JLabel id_cliente = new JLabel(cliente.getId());
		id_cliente.setBounds(300, 50, 500, 50);
		id_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(id_cliente);
		
		//NOME
		JLabel nome_cliente = new JLabel("NOME: ");
		nome_cliente.setBounds(50, 100, 250, 50);
		nome_cliente.setHorizontalAlignment(JLabel.RIGHT);
		nome_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(nome_cliente);
		
		JLabel nome_cliente_lbl = new JLabel(cliente.getNome());
		nome_cliente_lbl.setBounds(300, 100, 500, 50);
		nome_cliente_lbl.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(nome_cliente_lbl);
		
		//TELEFONE
		JLabel telefone_cliente_lbl = new JLabel("TELEFONE: ");
		telefone_cliente_lbl.setBounds(50, 150, 250, 50);
		telefone_cliente_lbl.setHorizontalAlignment(JLabel.RIGHT);
		telefone_cliente_lbl.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(telefone_cliente_lbl);
		
		JLabel telefone_cliente = new JLabel(cliente.getTel());
		telefone_cliente.setBounds(300, 150, 500, 50);
		telefone_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(telefone_cliente);
		
		//ADICIONAR
		JButton add = new JButton("ADICIONAR");
		add.setBounds(50, 205, 250, 40);
		add.setFont(new Font("Tahoma", Font.PLAIN, 35));
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Tela().showProdutos();
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		add.requestFocus();
		contentPane.add(add);
		
		//ALTERAR
		JButton alt = new JButton("ALTERAR");
		alt.setBounds(320, 205, 250, 40);
		alt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		contentPane.add(alt);
		
		//LISTA DE PRODUTOS
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 250, d.width-20, d.height-260);
			JList<String> lista = new JList<>(new AbstractListModel<>() {
				private static final long serialVersionUID = 1L;
				@Override
				public int getSize() {
					return cliente.getProdutos().size();
				}
				@Override
				public String getElementAt(int index) {
					String[] noID = cliente.getProdutos().get(index).split(":");
					return noID[1];
				}
			});
			lista.setBounds(scroll.getBounds());
			lista.setFont(new Font("Tahoma", Font.PLAIN, 45));
		scroll.add(lista);
		contentPane.add(scroll);
		
		
		
		this.setVisible(true);
	}
	
	public void showProdutos()throws SQLException{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//TITULO
		JLabel produtos = new JLabel("PRODUTOS");
		produtos.setBounds((d.width-250)/2, 50, 250, 50);
		produtos.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(produtos);
		
		//LISTA DE PRODUTOS
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 250, d.width-20, d.height-260);
			ArrayList<String> p = new ArrayList<>(new Banco().produtos());
			JList<String> lista = new JList<>(new AbstractListModel<>() {
				private static final long serialVersionUID = 1L;
				@Override
				public int getSize() {
					return p.size();
				}
				@Override
				public String getElementAt(int index) {
					return p.get(index);
				}
			});
			lista.setFont(new Font("Tahoma", Font.PLAIN, 45));
			lista.setBounds(scroll.getBounds());
			scroll.add(lista);
		contentPane.add(scroll);
		
		
		
		this.setVisible(true);
	}
}
