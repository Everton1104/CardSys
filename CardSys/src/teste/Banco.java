package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
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
		if(res.next()) {
			id = res.getString("id");
		}else {
			sql = "INSERT INTO cartao (numero) VALUES ("+numero_id+");";
			ps = conexao.prepareStatement(sql);
			ps.execute();
			ps.close();
			this.execute(numero_id);
			return null;
		}
		sql = "SELECT cartao.id AS id_cartao, cartao.numero AS numero_cartao, cartao.nome AS nome_cartao, cartao.telefone, " + 
				"produtos.id AS id_produto, produtos.nome_produto AS produto, produtos.valor, " + 
				"controle.qtde FROM controle " + 
				"LEFT JOIN cartao ON cartao.id = id_cartao " + 
				"LEFT JOIN produtos ON produtos.id = id_produto " + 
				"WHERE id_cartao = "+id+";";
		ps.close();
		ps = conexao.prepareStatement(sql);
		res = ps.executeQuery();
		res.next();
		cliente.setId(res.getString("id_cartao"));
		cliente.setNumero(res.getString("numero_cartao"));
		cliente.setNome(res.getString("nome_cartao"));
		cliente.setTelefone(res.getString("telefone"));
		do {
			ArrayList<String>produto =  new ArrayList<>();
			produto.add(res.getString("id_produto"));
			produto.add(res.getString("produto"));
			produto.add(res.getString("valor"));
			produto.add(res.getString("qtde"));
			produtos.add(produto);
		}while(res.next());
		cliente.setProdutos(produtos);
		ps.close();
		return cliente;
	}	
}