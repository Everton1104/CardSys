package teste;

import java.util.ArrayList;

public class Cliente {
	private String id;
	private String numero;
	private String nome;
	private String telefone;
	private ArrayList<Object> produtos;

	public ArrayList<Object> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Object> produtos) {
		this.produtos = produtos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
