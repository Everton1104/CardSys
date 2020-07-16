package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
	
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
		ArrayList<Object> produtos = new ArrayList<>();
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
		
		//separar em duas fazes a consulta de dados uma para pegar o nome e tel. e outra para lista de produtos.
		
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
		sql = "";//fazer consulta no banco para retornar os valores
		ps = conexao.prepareStatement(sql);
		res = ps.executeQuery();
		while(res.next()){
			ArrayList<Object>produto =  new ArrayList<>();
			produto.add(res.getString("id_produto"));
			produto.add(res.getString("produto"));
			produto.add(res.getString("valor"));
			produto.add(res.getString("qtde"));
			produtos.add(produto);
		}
		cliente.setProdutos(produtos);
		ps.close();
		System.out.println("Banco consulta-> "+cliente);
		return cliente;
	}	
}