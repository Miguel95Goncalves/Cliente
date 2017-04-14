package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Chat {

	private JFrame frame;
	private JTextField textMensagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat window = new Chat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Chat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/imagens/Chat-icon.png")));
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtrConversa = new JTextArea();
		txtrConversa.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		txtrConversa.setEditable(false);
		txtrConversa.setBackground(SystemColor.inactiveCaptionBorder);
		txtrConversa.setBounds(10, 11, 364, 225);
		frame.getContentPane().add(txtrConversa);
		
		textMensagem = new JTextField();
		textMensagem.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		textMensagem.setFont(new Font("Arial", Font.PLAIN, 12));
		textMensagem.setBackground(SystemColor.inactiveCaptionBorder);
		textMensagem.setBounds(10, 254, 270, 96);
		frame.getContentPane().add(textMensagem);
		textMensagem.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		btnEnviar.setFont(new Font("Century", Font.BOLD, 14));
		btnEnviar.setVerticalTextPosition(SwingConstants.TOP);
		btnEnviar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEnviar.setBackground(UIManager.getColor("Button.background"));
		btnEnviar.setIcon(new ImageIcon(Chat.class.getResource("/imagens/Actions-mail-receive-icon.png")));
		btnEnviar.setBounds(285, 253, 89, 97);
		frame.getContentPane().add(btnEnviar);
		
		JLabel lblFundo = new JLabel("Enviar");
		lblFundo.setBackground(UIManager.getColor("Button.background"));
		lblFundo.setIcon(new ImageIcon(Chat.class.getResource("/imagens/fundo.jpg")));
		lblFundo.setBounds(0, 0, 395, 361);
		frame.getContentPane().add(lblFundo);
	}
}
