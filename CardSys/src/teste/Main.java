package teste;

import java.util.ArrayList;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Banco b = new Banco();
		Cliente c = b.execute("26120142");
		System.out.println("id: "+c.getId());
		System.out.println("nome: "+c.getNome());
		System.out.println("telefone: "+c.getTelefone());
		ArrayList<Object> produtos = new ArrayList<>();
		produtos.addAll(c.getProdutos());
		ArrayList<ArrayList<String>> produto = new ArrayList<>();
		for(int i = 0; i < produtos.size(); i ++) {
			produto.add((ArrayList<String>)produtos.get(i));
		}
		for(int i = 0; i < produtos.size(); i++) {
			System.out.println("id produto: "+produto.get(i).get(0));
			System.out.println("nome produto: "+produto.get(i).get(1));
			System.out.println("valor: "+produto.get(i).get(2));
			System.out.println("qtde: "+produto.get(i).get(3));
			System.out.println("Total: "+(Float.parseFloat(produto.get(i).get(2)))*Float.parseFloat(produto.get(i).get(3)));
		}
		
		ArrayList<String> Listaprodutos =  new ArrayList<>();
		Listaprodutos = b.consulta("");
		System.out.println(Listaprodutos);
	}
}
