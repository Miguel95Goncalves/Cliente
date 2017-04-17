package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Mensagem;
import model.Mensagem.Acao;
import model.ServicoCliente;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatCliente extends JFrame {
	private JPanel contentPane;
	private JTextField txtNome;
	private Socket socket;
	private Mensagem mensagem;
	private ServicoCliente servico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatCliente frame = new ChatCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatCliente() {
		setTitle("ChatMenssager");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChatCliente.class.getResource("/imagens/Chat-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.activeCaptionText);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conectar", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlightText));
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(10, 11, 363, 50);
		contentPane.add(panel);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNome.setColumns(10);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			private Mensagem mensagem;
			private Socket socket;
			private ServicoCliente servico;

			public void actionPerformed(ActionEvent arg0) {
				String nome = txtNome.getText();
				
				if(!nome.isEmpty()){
					this.mensagem = new Mensagem();
					this.mensagem.setAcao(Acao.conectar);
					this.mensagem.setNome(nome);
					
					if(this.socket == null){
						this.servico = new ServicoCliente();
						this.socket = this.servico.conectar();
						
						new Thread(new ListenerSocket(this.socket)).start();
					}
					
					this.servico.enviar(mensagem);
				}
			}
		});
		btnConectar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSair.setFont(new Font("Arial Black", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnConectar)
					.addGap(18)
					.addComponent(btnSair)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSair)
						.addComponent(btnConectar))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(SystemColor.activeCaptionText);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Utilizadores", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlightText));
		panel_1.setBackground(SystemColor.desktop);
		panel_1.setBounds(383, 11, 141, 339);
		contentPane.add(panel_1);
		
		JList listUtilizadores = new JList();
		listUtilizadores.setForeground(new Color(0, 204, 51));
		listUtilizadores.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtualizar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(listUtilizadores, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
						.addComponent(btnAtualizar, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(listUtilizadores, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(SystemColor.activeCaptionText);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlightText));
		panel_2.setBackground(SystemColor.desktop);
		panel_2.setBounds(10, 72, 363, 278);
		contentPane.add(panel_2);
		
		JTextArea textAreaRecebido = new JTextArea();
		textAreaRecebido.setEditable(false);
		textAreaRecebido.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JTextArea textAreaEnviar = new JTextArea();
		textAreaEnviar.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApagar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(textAreaRecebido, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
						.addComponent(textAreaEnviar, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(159, Short.MAX_VALUE)
					.addComponent(btnApagar)
					.addGap(4)
					.addComponent(btnEnviar)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(textAreaRecebido, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaEnviar, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApagar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ChatCliente.class.getResource("/imagens/fundo.jpg")));
		label.setBounds(0, 0, 534, 361);
		contentPane.add(label);
	}

	private class ListenerSocket implements Runnable{

		private ObjectInputStream input;
		
		public ListenerSocket(Socket socket){
			try {
				this.input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			Mensagem mensagem = null;
			
			try {
				while((mensagem = (Mensagem) input.readObject()) != null){
					Acao acao = mensagem.getAcao();
					
					if(acao.equals(Acao.conectar)){
						conectar(mensagem);
					}else if(acao.equals(Acao.disconectar)){
						disconectar(mensagem);
					}else if(acao.equals(Acao.enviar_um)){
						enviar_um(mensagem);
					}else if(acao.equals(Acao.utilOn)){
						atualizar(mensagem);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void conectar(Mensagem mensagem){
		
	}
	
	private void disconectar(Mensagem mensagem){
		
	}

	private void enviar_um(Mensagem mensagem){
		
	}
	
	private void atualizar(Mensagem mensagem){
		
	}
}