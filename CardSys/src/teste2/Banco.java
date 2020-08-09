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
	
	public Cliente cliente(String id){
		try {
			PreparedStatement ps;
			ResultSet res ;
			String sql = "";
			
			if(Integer.parseInt(id)>10000){
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
			
			Cliente cliente = new Cliente();
			
			sql = "SELECT * FROM cartao WHERE id = "+id+";";
			ps = this.Con().prepareStatement(sql);
			res = ps.executeQuery();
			res.next();
			
			//dados do cliente
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
			return cliente;
		}catch(Exception e) {return null;}
	}	

	public ArrayList<String> produtos(String busca)throws SQLException{
		
		PreparedStatement ps;
		ResultSet res ;
		ArrayList<String> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM produtos WHERE nome_produto LIKE '%"+busca+"%';";
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		while(res.next()) {
			lista.add(res.getString("id")+": "+res.getString("nome_produto")+" R$ "+res.getString("valor"));
		}
		return lista;
	}
	
	public void setDados(Cliente c) throws SQLException{
		PreparedStatement ps;
		String sql = "UPDATE cartao SET nome = '"+c.getNome()+"' WHERE id = "+c.getId()+";";
		ps = this.Con().prepareStatement(sql);
		ps.executeQuery();
		sql = "UPDATE cartao SET telefone = '"+c.getTel()+"' WHERE id = "+c.getId()+";";
		ps = this.Con().prepareStatement(sql);
		ps.executeQuery();
	}
	
	public void add(String id, String produto,Integer qtde) throws SQLException{
		
		String[] p = produto.split(":");
		
		
		PreparedStatement ps;
		ResultSet res ;
		String sql = "SELECT * FROM controle WHERE id_cartao = "+id+" AND id_produto = "+p[0]+";";
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		if(res.next()) {
			sql = "UPDATE controle SET qtde = "+(Integer.parseInt(res.getString("qtde"))+qtde)+" WHERE id_cartao = "+id+" AND id_produto = "+p[0]+";";
			ps.executeQuery(sql);
		}else {
			sql = "INSERT INTO controle (id_cartao, id_produto, qtde) VALUES ("+id+", "+p[0]+", "+qtde+");";
			ps = this.Con().prepareStatement(sql);
			res = ps.executeQuery();
		}
	}
	
	public void alt(String produto, String qtde, String id)throws SQLException {
		PreparedStatement ps;
		ResultSet res ;
		String[] p = produto.split(" ");
		String sql = "SELECT * FROM produtos WHERE nome_produto LIKE '%"+p[3]+"%';";
		ps = this.Con().prepareStatement(sql);
		res = ps.executeQuery();
		res.next();
		try {
			produto = res.getString("id");
		}catch(Exception e) {System.out.println("ERRO!:nao foi encontrado nenhum produto.");return;}
		try {
			Integer intQtde = Integer.parseInt(qtde);
			if(intQtde <= 0 && intQtde != null) {
				sql = "DELETE FROM controle WHERE id_cartao = "+id+" AND id_produto = "+produto+";";
				ps = this.Con().prepareStatement(sql);
				ps.executeQuery();
			}else if(intQtde != null){
				sql = "UPDATE controle SET qtde = "+qtde+" WHERE id_cartao = "+id+" AND id_produto = "+produto+";";
				ps.executeQuery(sql);
			}
		}catch(Exception e) {return;}
	}
	
	public void pag(Cliente c) {
		try {
			String sql = "UPDATE cartao SET nome = 0 WHERE id = "+c.getId()+";";
			PreparedStatement ps = this.Con().prepareStatement(sql);
			ps.executeQuery();
			sql = "UPDATE cartao SET telefone = 0 WHERE id = "+c.getId()+";";
			ps = this.Con().prepareStatement(sql);
			ps.executeQuery();
			sql = "DELETE FROM controle WHERE id_cartao = "+c.getId()+";";
			ps = this.Con().prepareStatement(sql);
			ps.executeQuery();
		}catch(Exception e) {e.printStackTrace();}
	}
}