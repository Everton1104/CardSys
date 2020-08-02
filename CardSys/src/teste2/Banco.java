package teste2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
	
	public Connection Con() throws SQLException{
		
		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	public Cliente cliente(String id) throws SQLException {
		
		PreparedStatement ps;
		ResultSet res ;
		String sql = "";
		
		Cliente cliente = new Cliente();
		
		//detecta o tipo de id.
		if(Integer.parseInt(id)>1000){
			sql = "SELECT id FROM cartao WHERE numero = "+id+";";
			ps = this.Con().prepareStatement(sql);
			res = ps.executeQuery();
			if(res.next()) {
				id = res.getString("id");
			}else {
				sql = "INSERT INTO cartao (numero) VALUES ("+id+");";
				ps = this.Con().prepareStatement(sql);
				res = ps.executeQuery();
				sql = "SELECT id FROM cartao WHERE numero = "+id+";";
				ps = this.Con().prepareStatement(sql);
				res = ps.executeQuery();
				res.next();
				id = res.getString("id");
			}
		}
		sql = "SELECT * FROM cartao WHERE id= "+id+";";
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		res.next();
		
		//dados do cliente.
		cliente.setId(res.getString("id"));
		cliente.setNome(res.getString("nome"));
		cliente.setTel(res.getString("telefone"));
		
		//lista de produtos do cliente
		sql = "SELECT produtos.id, produtos.nome_produto AS nome, controle.qtde ,produtos.valor FROM controle " + 
				" LEFT JOIN produtos ON id_produto = produtos.id " + 
				" WHERE id_cartao = "+id+";";
		ArrayList<String> lista = new ArrayList<>();
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		while(res.next()) {
			lista.add(res.getString("id")+": "+res.getString("qtde")+" X "+res.getString("nome")+" R$ "+Float.parseFloat(res.getString("valor")));
		}
		cliente.setProdutos(lista);
		System.out.println(lista);
		return cliente;
	}	
	
	public ArrayList<String> produtos()throws SQLException{
		
		PreparedStatement ps;
		ResultSet res ;
		ArrayList<String> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM produtos;";
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		while(res.next()) {
			lista.add(res.getString("id")+": "+res.getString("nome_produto")+" R$ "+res.getString("valor"));
		}
		return lista;
	}
}