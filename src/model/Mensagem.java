package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Mensagem implements Serializable {
	private String nome;
	private String mensagem;
	private String nomeReservado;
	private Set<String> utilOn = new HashSet<String>();
	private Acao acao;
	
	public enum Acao{
		conectar, disconectar, enviar_um, enviar_todos, utilOn
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeReservado() {
		return nomeReservado;
	}

	public void setNomeReservado(String nomeReservado) {
		this.nomeReservado = nomeReservado;
	}

	public Set<String> getUtilOn() {
		return utilOn;
	}

	public void setUtilOn(Set<String> utilOn) {
		this.utilOn = utilOn;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
}
