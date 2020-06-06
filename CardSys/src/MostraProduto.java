import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MostraProduto extends JFrame{
	String opc = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public String mostra(String IDP) {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRODUTOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(267, 88, 420, 106);
		contentPane.add(lblNewLabel);
		
		JButton btnFim = new JButton("Finalizar");
		btnFim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opc = "fim";
			}
		});
		btnFim.setFont(new Font("Tahoma", Font.PLAIN, 60));
		btnFim.setBounds(63, 365, 371, 106);
		contentPane.add(btnFim);
		
		JButton btnAdd = new JButton("Adicionar\r\n");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				return;
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 60));
		btnAdd.setBounds(523, 365, 371, 106);
		contentPane.add(btnAdd);
		
		return opc;
	}

}
