package br.com.CardSys;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Window;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MontaTela extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField telefone;
	private JTextField txtTeste;
	private JTextField txtBusca;
	
	public MontaTela() throws Exception{
		inicio();
		//cliente("2612014");//COMENTAR IF e finalizar manualmente por causa do hide on close.
		//produto();
	}
	

	public void inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(271, 46, 419, 130);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passe o cartao");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(271, 267, 419, 130);
		contentPane.add(lblNewLabel_1);
	}
	public void cliente(String id)throws SQLException {
		
		Banco b = new Banco();
		ArrayList<String> cartao_numero = b.execute(id, "cartao_numero", "", "");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cart\u00E3o numero:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(10, 10, 389, 86);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(cartao_numero.get(0));
		lblNewLabel_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1_1.setBounds(391, 10, 497, 86);
		contentPane.add(lblNewLabel_1_1);
		
		
		if(cartao_numero.get(1).contentEquals("0")==false) {
			JLabel lblNewLabel_1_2 = new JLabel("Cliente:");
			lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_2.setBounds(10, 81, 389, 86);
			contentPane.add(lblNewLabel_1_2);
		
			JLabel lblNewLabel_1_1_1 = new JLabel(cartao_numero.get(1));
			lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_1_1.setForeground(Color.ORANGE);
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_1_1.setBounds(391, 81, 497, 86);
			contentPane.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("Telefone:");
			lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_2_1.setBounds(10, 154, 389, 86);
			contentPane.add(lblNewLabel_1_2_1);
			
			JLabel lblNewLabel_1_1_1_1 = new JLabel(cartao_numero.get(2));
			lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1_1_1_1.setForeground(Color.ORANGE);
			lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_1_1_1.setBounds(391, 154, 497, 86);
			contentPane.add(lblNewLabel_1_1_1_1);
			
			JButton btnAdd = new JButton("ADICIONAR PRODUTO");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dispose();
						MontaTela p = new MontaTela();
						p.produto(id);
						p.setVisible(true);
					}catch (Exception err) {System.out.println("erro add produto.");err.printStackTrace();}
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
			btnAdd.setBounds(20, 234, 332, 59);
			contentPane.add(btnAdd);
			btnAdd.requestFocus();
			btnAdd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					int keyCode = e.getKeyCode();
					if(keyCode == KeyEvent.VK_ENTER) {
						try {
							dispose();
							MontaTela p = new MontaTela();
							p.produto(id);
							p.setVisible(true);
						}catch (Exception err) {System.out.println("erro add produto.");err.printStackTrace();}
					}
				}});
			
			
			JButton btnNewButton = new JButton("PAGAR CONTA");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(JOptionPane.showConfirmDialog(null, "Pagar conta de "+cartao_numero.get(1)+"?", "Pagamento", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
							b.execute(id, "limpar", "", "");
							dispose();
						}
					} catch (SQLException e1) {e1.printStackTrace();}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btnNewButton.setBounds(362, 232, 295, 59);
			contentPane.add(btnNewButton);
			
			JButton btnEditar = new JButton("APAGAR ITEM");
			btnEditar.setEnabled(false);
			btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btnEditar.setBounds(667, 232, 295, 59);
			contentPane.add(btnEditar);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setBounds(10, 317, 979, 389);
			ArrayList<String> dados = b.execute(id, "consulta", "", "");
			JList<String> list = new JList<>(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				public int getSize() {
					return dados.size();
				}
				public String getElementAt(int index) {
					return dados.get(index);
				}
			});
			list.setFont(new Font("Tahoma", Font.PLAIN, 30));
			list.setBounds(10, 321, 979, 385);
			
			list.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					btnEditar.setEnabled(true);
					btnEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String item = list.getSelectedValue();
							try {
								b.execute(id, "apagar_item", item, "");
								dispose();
								cliente(id);
								
							} catch (Exception e1) {System.out.println("Erro apagar item do banco");e1.printStackTrace();}
						}
					});
				}
			});
			
			scrollPane.add(list);
			contentPane.add(scrollPane);
			
		}else {
			JLabel lblNewLabel_1_3 = new JLabel("Nome:");
			lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_3.setBounds(50, 210, 389, 86);
			contentPane.add(lblNewLabel_1_3);
			
			JLabel lblNewLabel_1_3_1 = new JLabel("Telefone:");
			lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_3_1.setBounds(50, 370, 389, 86);
			contentPane.add(lblNewLabel_1_3_1);
			
			nome = new JTextField();
			nome.setHorizontalAlignment(SwingConstants.LEFT);
			nome.setFont(new Font("Tahoma", Font.PLAIN, 40));
			nome.setBounds(474, 210, 489, 86);
			contentPane.add(nome);
			nome.setColumns(10);
			nome.addActionListener(event -> {
			try {
				b.execute(id, "add_nome", nome.getText()+"", "");
				dispose();
				this.cliente(id);
				this.setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}});
			
			telefone = new JTextField();
			telefone.setHorizontalAlignment(SwingConstants.LEFT);
			telefone.setFont(new Font("Tahoma", Font.PLAIN, 40));
			telefone.setColumns(10);
			telefone.setBounds(474, 370, 489, 86);
			contentPane.add(telefone);
			telefone.addActionListener(event -> {
			try {
				b.execute(id, "add_nome", nome.getText()+"",telefone.getText()+"");
				dispose();
				this.cliente(id);
				this.setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}});

			JButton btnOK = new JButton("OK");
			btnOK.setFont(new Font("Tahoma", Font.PLAIN, 60));
			btnOK.setBounds(353, 500, 270, 130);
			contentPane.add(btnOK);
			btnOK.addActionListener(event -> {
				try {
					b.execute(id, "add_nome", nome.getText()+"", telefone.getText()+"");
					dispose();
					this.cliente(id);
					this.setVisible(true);
				} catch (SQLException e) {e.printStackTrace();}
			});
			
		}
	}


	public void produto(String id) throws SQLException{

		Banco b = new Banco();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADICIONAR NOVOS PRODUTOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 10, 888, 130);
		contentPane.add(lblNewLabel);
		
		txtTeste = new JTextField();
		txtTeste.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtTeste.setBounds(55, 175, 888, 58);
		contentPane.add(txtTeste);
		txtTeste.setColumns(10);
		txtTeste.requestFocus();
		
		txtBusca = new JTextField();
		txtBusca.setEditable(false);
		txtBusca.setFont(new Font("Tahoma", Font.PLAIN, 45));
		txtBusca.setBounds(55, 275, 888, 58);
		contentPane.add(txtBusca);
		txtBusca.setColumns(10);
		
		txtTeste.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int k =e.getKeyCode();
				ArrayList<String> res = new ArrayList<>();
				String letra = txtTeste.getText()+ e.getKeyChar();
				if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE && k!=KeyEvent.VK_ENTER) {
					try {
						res = b.execute(id, "parcial", letra, "");
						txtBusca.setText(res.get(2)+res.get(0)+" | Valor -> R$"+Float.parseFloat(res.get(1))+"0");
					} catch (SQLException e1) {
						System.out.println("Erro banco parcial.");
						txtBusca.setText("Nada encontrado.");
					}
				}else if(k!=KeyEvent.VK_BACK_SPACE && k!=KeyEvent.VK_DELETE &&k == KeyEvent.VK_ENTER) {
					try {
						letra = txtTeste.getText();
						res = b.execute(id, "parcial", letra, "");
						String qtde = JOptionPane.showInputDialog(null, "Quantas unidades de "+res.get(0)+"?", "Quantidade", JOptionPane.QUESTION_MESSAGE);
						if(res.size()>0&&(!qtde.isBlank()||!qtde.isEmpty())) {
							b.execute(id, "add_pedido", res.get(2), qtde);
						}
						dispose();
						MontaTela c =  new MontaTela();
						c.cliente(id);
						c.setVisible(true);
						
					} catch (Exception eQtde) {System.out.println("Erro na qtde!");eQtde.printStackTrace();}
				}
			}});
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PARA CONFIRMAR");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_1.setBounds(55, 370, 888, 130);
		contentPane.add(lblNewLabel_1);
	}
}
