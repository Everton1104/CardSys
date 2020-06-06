import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MostraCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public String mostra(String ID) {
		
		Banco banco = new Banco();
		banco.bancoInit(ID);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mostra Cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(138, 84, 390, 106);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passe o cartao produto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1.setBounds(263, 293, 470, 118);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2.setBounds(33, 177, 95, 125);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(ID);
		lblNewLabel_2_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2_1.setBounds(122, 177, 854, 125);
		contentPane.add(lblNewLabel_2_1);
		
		
		return ID;
	}
	

}
