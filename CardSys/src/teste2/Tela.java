package teste2;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
					new Tela().showProdutos(cliente);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		add.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						new Tela().showProdutos(cliente);
						dispose();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		contentPane.add(add);
		
		//ALTERAR
		JButton alt = new JButton("ALTERAR");
		alt.setBounds(320, 205, 250, 40);
		alt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		alt.setEnabled(false);
		contentPane.add(alt);
		
		//DELETAR
		JButton del = new JButton("DELETAR");
		del.setBounds(590, 205, 250, 40);
		del.setEnabled(false);
		del.setFont(new Font("Tahoma", Font.PLAIN, 35));
		del.setEnabled(false);
		contentPane.add(del);
		
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
			lista.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					alt.setEnabled(true);
					del.setEnabled(true);
				}
			});
		alt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String qtde = JOptionPane.showInputDialog(null, "Valor anterior: "+lista.getSelectedValue()+"\nDigite a nova quantidade:", "ALTERAR PRODUTO", JOptionPane.OK_CANCEL_OPTION);
				try {
					new Banco().alt(lista.getSelectedValue(), qtde, cliente.getId());
					dispose();
					new Tela().showCliente(new Banco().cliente(cliente.getId()));
				}catch(Exception e2) {e2.printStackTrace();}
			}
		});
		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer opc = JOptionPane.showConfirmDialog(null, "DELETAR "+lista.getSelectedValue()+"?","DELETAR", JOptionPane.YES_NO_OPTION);
				if(opc==JOptionPane.YES_OPTION) {
					try {
						new Banco().alt(lista.getSelectedValue(), "0", cliente.getId());
						dispose();
						new Tela().showCliente(new Banco().cliente(cliente.getId()));
					}catch(Exception e2) {e2.printStackTrace();}
				}
			}
		});
		scroll.add(lista);
		contentPane.add(scroll);
		
		this.setVisible(true);
	}
	
	public void showProdutos(Cliente c)throws SQLException{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//TITULO
		JLabel produtos = new JLabel("ADICIONAR PRODUTOS");
		produtos.setBounds((d.width-500)/2, 50, 500, 50);
		produtos.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(produtos);
		
		//ADICIONAR
		JLabel add = new JLabel("Digite o nome do produto e pressione [ENTER] para confirmar");
		add.setBounds(0, 125, d.width, 50);
		add.setHorizontalAlignment(JLabel.CENTER);
		add.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(add);
		
		//PESQUISA
		JTextField busca = new JTextField();
		busca.setBounds(10, 190, d.width-20, 50);
		busca.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(busca);
		
		//LISTA DE PRODUTOS
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 250, d.width-20, d.height-260);
			JList<String> lista = new JList<>();
			lista.setFont(new Font("Tahoma", Font.PLAIN, 45));
			lista.setBounds(scroll.getBounds());
			busca.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER && !busca.getText().isBlank()) {
						try {
							String qtde = JOptionPane.showInputDialog(null, "QUANTIDADE?", lista.getSelectedValue()+"?", JOptionPane.OK_CANCEL_OPTION);
							Integer qt = Integer.parseInt(qtde);
							if(qt>0) {
								new Banco().add(c.getId(), lista.getSelectedValue(), Integer.parseInt(qtde));
								dispose();
								new Tela().showCliente(new Banco().cliente(c.getId()));
							}
						}catch(Exception e2){
							dispose();
							JOptionPane.showConfirmDialog(null, "NAO DIGITE LETRAS EM QUANTIDADE\n SOMENTE NUMEROS!","ERRO!",JOptionPane.ERROR_MESSAGE);
							try {
								new Tela().showCliente(new Banco().cliente(c.getId()));
							}catch(Exception e3) {e3.printStackTrace();}
						}
					}else {
						try {	
							ArrayList<String> p = new Banco().produtos(busca.getText().trim());
							AbstractListModel<String> modelo = new AbstractListModel<>() {
								private static final long serialVersionUID = 1L;
								@Override
								public int getSize() {
									return p.size();
								}
								@Override
								public String getElementAt(int index) {
									return p.get(index);
								}
							};
							lista.setModel(modelo);
							lista.setSelectedIndex(0);
						}catch(Exception e3) {e3.printStackTrace();}
					}
				}
			});
			lista.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					busca.requestFocus();
				}
			});
			scroll.add(lista);
		contentPane.add(scroll);
		this.setVisible(true);
	}
}
