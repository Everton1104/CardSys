package br.com.CardSys;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MontaTela extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField telefone;
	
	public MontaTela() throws Exception{
		inicio();
		//cliente("92518414");
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
		ArrayList<String> cartao_numero = b.execute(id, "cartao_numero", "0", "0");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setBounds(10, 317, 979, 389);
			JList<String> list = new JList<>(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				String[] values = new String[] {
						"",
						"",
						""
						};
				public int getSize() {
					return values.length;
				}
				public String getElementAt(int index) {
					return values[index];
				}
			});
			list.setFont(new Font("Tahoma", Font.PLAIN, 30));
			list.setBounds(10, 321, 979, 385);
			scrollPane.add(list);
			contentPane.add(scrollPane);
			
			JButton btnNewButton = new JButton("PAGAR");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						b.execute(id, "limpar", "", "");
						System.exit(HIDE_ON_CLOSE);
					} catch (SQLException e1) {e1.printStackTrace();}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btnNewButton.setBounds(10, 234, 216, 61);
			contentPane.add(btnNewButton);
			
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
				System.exit(HIDE_ON_CLOSE);
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
				System.exit(HIDE_ON_CLOSE);
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
					System.exit(HIDE_ON_CLOSE);
				} catch (SQLException e) {e.printStackTrace();}
			});
			
		}
	}
}
