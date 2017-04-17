package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServicoCliente {
	private Socket socket;
	private ObjectOutputStream output;
	
	public Socket conectar(){
		try {
			this.socket = new Socket("localhost", 2017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return socket;
	}
	
	public void enviar(Mensagem mensagem){
		try {
			output.writeObject(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
