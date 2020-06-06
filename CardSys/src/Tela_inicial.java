import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Tela_inicial extends JFrame {
	public Tela_inicial() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		Arduino ard = new Arduino();
		Tela_inicial frame = new Tela_inicial();
		MostraCliente frameCliente =  new MostraCliente();
		MostraProduto frameProduto = new MostraProduto();
		while(true) {
			frame.montaTela();
			frame.setVisible(true);
			String ID = ard.ler();
			frameCliente.mostra(ID);
			frameCliente.setVisible(true);
			String IDP = ard.ler();
			frameProduto.mostra(IDP);
			frameProduto.setVisible(true);
		}
	}
	
	public void montaTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(389, 86, 233, 106);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passe o cart\u00E3o");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_1.setBounds(165, 300, 657, 106);
		contentPane.add(lblNewLabel_1);
	}
}
