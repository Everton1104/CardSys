package teste2;

import java.util.ArrayList;

public class Cliente {
	private String id;
	private String nome;
	private String tel;
	private ArrayList<String> produtos = new ArrayList<>();
	
	@Override
	public String toString() {
		System.out.println("Id: "+id);
		System.out.println("Nome: "+nome);
		System.out.println("Telefone: "+tel);
		System.out.println("---Lista de produtos de "+nome+"---");
		for (String produto : produtos) {
			System.out.println(produto);
		}
		return "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ArrayList<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<String> produtos) {
		this.produtos = produtos;
	}

}
