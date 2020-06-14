package br.com.CardSys;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import jdk.jfr.Event;

import javax.swing.JButton;

public class MontaTela extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField telefone;
	
	public MontaTela() throws Exception{
		inicio();
		//cliente("teste");
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
		ArrayList<String> cliente = b.execute(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cart\u00E3o numero:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(50, 33, 389, 86);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(id);
		lblNewLabel_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1_1.setBounds(466, 33, 497, 86);
		contentPane.add(lblNewLabel_1_1);
		
		
		if(cliente.get(0).contentEquals("0")==false) {
			JLabel lblNewLabel_1_2 = new JLabel("Cliente:");
			lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_2.setBounds(50, 180, 389, 86);
			contentPane.add(lblNewLabel_1_2);
		
			JLabel lblNewLabel_1_1_1 = new JLabel(cliente.get(0));
			lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1_1.setForeground(Color.ORANGE);
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_1_1.setBounds(466, 180, 497, 86);
			contentPane.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("Telefone:");
			lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_2_1.setBounds(50, 326, 389, 86);
			contentPane.add(lblNewLabel_1_2_1);
			
			JLabel lblNewLabel_1_1_1_1 = new JLabel(cliente.get(1));
			lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1_1_1.setForeground(Color.ORANGE);
			lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel_1_1_1_1.setBounds(466, 326, 497, 86);
			contentPane.add(lblNewLabel_1_1_1_1);
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
			//nome.addActionListener(event -> System.out.println(nome.getText()));
			
			telefone = new JTextField();
			telefone.setHorizontalAlignment(SwingConstants.LEFT);
			telefone.setFont(new Font("Tahoma", Font.PLAIN, 40));
			telefone.setColumns(10);
			telefone.setBounds(474, 370, 489, 86);
			contentPane.add(telefone);
			//telefone.addActionListener(event -> System.out.println(telefone.getText()));

			JButton btnOK = new JButton("OK");
			btnOK.setFont(new Font("Tahoma", Font.PLAIN, 60));
			btnOK.setBounds(353, 500, 270, 130);
			contentPane.add(btnOK);
			btnOK.addActionListener(event -> System.out.println(nome.getText()));
		}
	}
}
