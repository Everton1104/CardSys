package teste2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	public void inicio() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//TITULO
		JLabel inicio = new JLabel("INICIO");
		inicio.setBounds((d.width-250)/2, 50, 500, 50);
		inicio.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(inicio);
		
		this.setVisible(true);
	}
	
	public void showCliente(Cliente cliente){
		
		if(cliente == null) {
			JOptionPane.showConfirmDialog(null, "Numero de cartao nao existe!","Erro!", JOptionPane.OK_CANCEL_OPTION);
			new Tela().showCliente(new Banco().cliente(new Arduino().ler()));
		}
		
		//VERIFICA SE NOVO CLIENTE
		if(cliente.getNome().contentEquals("0") || cliente.getNome().isEmpty()) {
			try {
				cliente.setNome(JOptionPane.showInputDialog(null, "Digite o nome do cliente:", "Novo cliente", JOptionPane.OK_CANCEL_OPTION));
				String tel = JOptionPane.showInputDialog(null,"Telefone (somente numeros com DDD):", "Telefone", JOptionPane.OK_CANCEL_OPTION);
				if(tel.length()==11) {
					tel = new FormatString().fs(tel, "(##) # ####-####");
				}else if(tel.length()==10){
					tel = new FormatString().fs(tel, "(##) ####-####");
				}else if(tel.length()==9) {
					tel = new FormatString().fs(tel, "# ####-####");
				}else {
					tel = new FormatString().fs(tel, "####-####");
				}
				cliente.setTel(tel);
				if(cliente.getNome().isEmpty()) {
					cliente.setNome("Sem Nome");
					new Banco().setDados(cliente);
				}
				if(cliente.getTel().isEmpty()) {
					cliente.setTel("Sem Telefone");
					new Banco().setDados(cliente);
				}
				new Banco().setDados(cliente);
			}catch(Exception e) {e.printStackTrace();}
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//ID
		JLabel id_cliente_lbl = new JLabel("CARTÃO: ");
		id_cliente_lbl.setBounds(50, 50, 250, 50);
		id_cliente_lbl.setHorizontalAlignment(JLabel.RIGHT);
		id_cliente_lbl.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(id_cliente_lbl);
		
		JLabel id_cliente = new JLabel(cliente.getId());
		id_cliente.setBounds(300, 50, 500, 50);
		id_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		id_cliente.setForeground(Color.ORANGE);
		contentPane.add(id_cliente);
		
		//NOME
		JLabel nome_cliente = new JLabel("NOME: ");
		nome_cliente.setBounds(50, 100, 250, 50);
		nome_cliente.setHorizontalAlignment(JLabel.RIGHT);
		nome_cliente.setFont(new Font("Tahoma", Font.PLAIN, 45));
		contentPane.add(nome_cliente);
		
		JLabel nome_cliente_lbl = new JLabel(cliente.getNome());
		nome_cliente_lbl.setBounds(300, 100, 500, 60);
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
		
		
		
		//ALTERAR
		JButton alt = new JButton("ALTERAR");
		alt.setBounds(320, 205, 250, 40);
		alt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		alt.setForeground(Color.ORANGE);
		alt.setEnabled(false);
		contentPane.add(alt);
		
		//DELETAR
		JButton del = new JButton("DELETAR");
		del.setBounds(590, 205, 250, 40);
		del.setFont(new Font("Tahoma", Font.PLAIN, 35));
		del.setForeground(Color.RED);
		del.setEnabled(false);
		contentPane.add(del);
		
		//PAGAR
		JButton pag = new JButton("PAGAR");
		pag.setBounds(860, 205, 250, 40);
		pag.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pag.setForeground(Color.GREEN);
		pag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Tela().pagamento(cliente);
				dispose();
			}
		});
		contentPane.add(pag);
		
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
				String qtde = JOptionPane.showInputDialog(null, lista.getSelectedValue()+"\nDigite a nova quantidade:", "ALTERAR PRODUTO", JOptionPane.OK_CANCEL_OPTION);
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
					}else if(e.getKeyCode()==KeyEvent.VK_P) {
						new Tela().pagamento(cliente);
						dispose();
					}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
						lista.requestFocus();
					}
				}
			});
			lista.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					add.requestFocus();
					if(e.getClickCount()==2) {
						alt.doClick();
					}
				}
			});
			add.requestFocus();
			contentPane.add(add);
		this.setVisible(true);
	}
	
	public void showProdutos(Cliente c)throws SQLException{
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		JLabel add = new JLabel("[ENTER] para confirmar ou [ESC] para cancelar");
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
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						dispose();
						new Tela().showCliente(new Banco().cliente(c.getId()));
					}else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {
							String qtde = JOptionPane.showInputDialog(null, "QUANTIDADE?", lista.getSelectedValue()+"?", JOptionPane.OK_CANCEL_OPTION);
							Integer qt = Integer.parseInt(qtde);
							if(qt>0) {
								new Banco().add(c.getId(), lista.getSelectedValue(), qt);
								dispose();
								new Tela().showCliente(new Banco().cliente(c.getId()));
							}
						}catch(Exception e2){
							JOptionPane.showConfirmDialog(null, "NAO DIGITE LETRAS EM QUANTIDADE\n SOMENTE NUMEROS!","ERRO!",JOptionPane.ERROR_MESSAGE);
							try {
								new Tela().showProdutos(c);
							}catch(Exception e3) {e3.printStackTrace();}
							dispose();
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
					lista.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							busca.requestFocus();
						}
					});
				}
			});
			scroll.add(lista);
		contentPane.add(scroll);
		this.setVisible(true);
	}
	
	public void pagamento(Cliente c) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds((d.width/2)-512, 50, 1024, 768);
		setResizable(false);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//TITULO
		JLabel produtos = new JLabel("RESUMO");
		produtos.setBounds((this.getWidth()/2)-75, 5, 150, 60);
		produtos.setFont(new Font("Tahoma", Font.PLAIN, 35));
		contentPane.add(produtos);
		
		//TOTAL
		JLabel total = new JLabel("Total:");
		total.setBounds(10, 75, 200, 70);
		total.setHorizontalAlignment(JLabel.RIGHT);
		total.setFont(new Font("Tahoma", Font.PLAIN, 50));
		contentPane.add(total);
		
		Float val = 0f;
		for (String produto : c.getProdutos()) {
			String[] p = produto.split(" ");
			val += Integer.parseInt(p[1]) * Float.parseFloat(p[5]);
		}
		
		JLabel valor = new JLabel("R$ "+val.toString());
		valor.setBounds(220, 75, 1000, 70);
		valor.setFont(new Font("Tahoma", Font.PLAIN, 50));
		valor.setForeground(Color.ORANGE);
		contentPane.add(valor);
		
		//PAGAR
		JButton pagar = new JButton("PAGAR");
		pagar.setBounds(10, 145, 150, 40);
		pagar.setFont(new Font("Tahoma", Font.PLAIN, 35));
		pagar.setForeground(Color.GREEN);
		pagar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_P) {
					if(JOptionPane.showConfirmDialog(null, "Confirmar pagamento?", "Confirmar.", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						new Banco().pag(c);
						dispose();
						try {
							new Tela().showCliente(new Banco().cliente(new Arduino().ler()));
						}catch(Exception e2) {e2.printStackTrace();}
					}else {
						dispose();
						try {
							new Tela().showCliente(new Banco().cliente(c.getId()));
						}catch(Exception e2) {e2.printStackTrace();}
					}
				}
			}
		});
		pagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Confirmar pagamento?", "Confirmar.", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					new Banco().pag(c);
					dispose();
					try {
						new Tela().showCliente(new Banco().cliente(new Arduino().ler()));
					}catch(Exception e2) {e2.printStackTrace();}
				}else {
					dispose();
					try {
						new Tela().showCliente(new Banco().cliente(c.getId()));
					}catch(Exception e2) {e2.printStackTrace();}
				}
			}
		});
		contentPane.add(pagar);
		
		//CANCELAR
		JButton cancelar = new JButton("CANCELAR");
		cancelar.setBounds(180, 145, 200, 40);
		cancelar.setFont(new Font("Tahoma", Font.PLAIN, 35));
		cancelar.setForeground(Color.RED);
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new Tela().showCliente(new Banco().cliente(c.getId()));
				}catch(Exception e2) {e2.printStackTrace();}
			}
		});
		contentPane.add(cancelar);
		
		//LISTA
		ScrollPane scroll = new ScrollPane();
		scroll.setBounds(10, 200, this.getWidth()-35, this.getHeight()-250);
			JList<String> lista = new JList<>(new AbstractListModel<>() {
				private static final long serialVersionUID = 1L;
				@Override
				public int getSize() {
					return c.getProdutos().size();
				}
				@Override
				public String getElementAt(int index) {
					String[] noID = c.getProdutos().get(index).split(":");
					return noID[1];
				}
			});
			lista.setBounds(scroll.getBounds());
			lista.setFont(new Font("Tahoma", Font.PLAIN, 35));
		scroll.add(lista);
		contentPane.add(scroll);
		
		this.setVisible(true);
	}
}