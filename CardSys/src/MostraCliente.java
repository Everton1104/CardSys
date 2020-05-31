import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MostraCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		while(true){
			Arduino ard = new Arduino();
			String id = ard.ler();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MostraCliente frame = new MostraCliente(id);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Create the frame.
	 */
	public MostraCliente(String ID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cartao Numero:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(65, 30, 422, 64);
		contentPane.add(lblNewLabel);
		
		JLabel IDcartao = new JLabel(ID);
		IDcartao.setHorizontalAlignment(SwingConstants.CENTER);
		IDcartao.setForeground(Color.ORANGE);
		IDcartao.setFont(new Font("Tahoma", Font.PLAIN, 70));
		IDcartao.setBounds(10, 104, 990, 130);
		contentPane.add(IDcartao);
	}

}
