package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
	
	public void add(String id_produto, String id_cliente, String qtde) throws SQLException {
		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";
		Connection conexao = DriverManager.getConnection(url, user, password);
		PreparedStatement ps;
		ResultSet res ;
		
	}
	
	public ArrayList<String> consulta(String busca) throws SQLException {
		
		ArrayList<String> produtos = new ArrayList<>();
		
		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";
		Connection conexao = DriverManager.getConnection(url, user, password);
		PreparedStatement ps;
		ResultSet res ;
		
		if(busca.isBlank()||busca.isEmpty()) {
			String sql = "SELECT * FROM produtos;";
			ps = conexao.prepareStatement(sql);
			res = ps.executeQuery();
			while(res.next()) {
				produtos.add(res.getString("nome_produto")+" R$ "+Float.parseFloat(res.getString("valor"))+"0");
			}
			System.out.println("Banco consulta-> "+produtos);
			return produtos;
		}else {
			String sql = "SELECT * FROM produtos WHERE nome_produto LIKE '%"+busca+"%';";
			ps = conexao.prepareStatement(sql);
			res = ps.executeQuery();
			while(res.next()) {
				produtos.add(res.getString("nome_produto")+" R$ "+Float.parseFloat(res.getString("valor"))+"0");
			}
			System.out.println("Banco consulta-> "+produtos);
			return produtos;
		}
	}
	
	public Cliente execute(String numero_id) throws SQLException {
		
		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";
		Connection conexao = DriverManager.getConnection(url, user, password);
		PreparedStatement ps;
		ResultSet res ;
		String id = "";
		Cliente cliente =  new Cliente();
		ArrayList<String> produtos = new ArrayList<>();
		String sql = "SELECT id FROM cartao WHERE numero = "+numero_id+";";
		ps = conexao.prepareStatement(sql);
		res = ps.executeQuery();
		res.next();
		if(!res.getString("id").isEmpty()) {
			id = res.getString("id");
		}else {
			sql = "INSERT INTO cartao (numero) VALUES ("+numero_id+");";
			ps = conexao.prepareStatement(sql);
			ps.execute();
			ps.close();
			this.execute(numero_id);
			System.out.println("Banco consulta-> Novo cartao adicionado!");
			return null;
		}
		
		sql = "SELECT * FROM cartao WHERE id = "+id+";";
		ps.close();
		ps = conexao.prepareStatement(sql);
		res = ps.executeQuery();
		res.next();
		cliente.setId(res.getString("id"));
		cliente.setNumero(res.getString("numero"));
		cliente.setNome(res.getString("nome"));
		cliente.setTelefone(res.getString("telefone"));
		ps.close();
		sql = "SELECT produtos.id, produtos.nome_produto AS nome, controle.qtde ,produtos.valor FROM controle " + 
				"LEFT JOIN produtos ON id_produto = produtos.id " + 
				"WHERE id_cartao = "+id+";";
		ps = conexao.prepareStatement(sql);
		res = ps.executeQuery();
		while(res.next()){
			ArrayList<String>produto =  new ArrayList<>();
			produto.add(res.getString("id"));
			produto.add(res.getString("nome"));
			produto.add(res.getString("qtde"));
			produto.add(res.getString("valor"));
			produtos.addAll(produto);
		}
		cliente.setProdutos(produtos);
		ps.close();
		System.out.println("Banco consulta produtos-> "+cliente.getProdutos());
		return cliente;
	}	
}