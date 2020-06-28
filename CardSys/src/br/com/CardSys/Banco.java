package br.com.CardSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
	public ArrayList<String> execute(String cartao_numero, String opc, String str1, String str2) throws SQLException {

		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";

		Connection conexao = DriverManager.getConnection(url, user, password);
		String sql = "SELECT id FROM cartao WHERE numero = "+cartao_numero+";";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		String id = "";
		if(res.next()) {
			id = res.getString("id");
		}else {
			cadastro_cartao(cartao_numero, conexao);
		}
			
		switch (opc) {
		case "add_nome": {
			add_nome(id, conexao, str1, str2);//str1=nome str2=telefone.
			return null;
		}
		case "add_pedido": {
			add_pedido(id, conexao, str1, str2);//str1=produto str2=qtde.
			return null;
		}
		case "consulta": {//reotorna get(0)=produto, get(1)=qtde, get(2)=valor, get(n)=n.
			return consulta(id, conexao);
		}
		case "consultaProduto": {
			return consultaProduto(id, conexao);
		}
		case "limpar": {
			limpar(id, conexao);
			return null;
		}
		case "cartao_numero": {//reotorna get(0)=id, get(1)=nome, get(2)=telefone.
			return consulta_cartao(cartao_numero, conexao);
		}
		case "parcial": {
			return parcial(str1, conexao);
		}
		case "alterar_item": {
			alterar_item(id, conexao, str1, str2);
			return null;
		}
		default:
			throw new IllegalArgumentException("Valor inesperado: " + opc);
		}
	}

	private ArrayList<String> consultaProduto(String id, Connection con)throws SQLException {
		String sql = "SELECT produtos.nome_produto AS produto FROM produtos;";
		PreparedStatement ps = con.prepareStatement(sql);
		ArrayList<String> consultaProduto = new ArrayList<String>();
		ResultSet res = ps.executeQuery();
		while (res.next()) {
			consultaProduto.add(res.getString("produto"));
		}
		return consultaProduto;
	}

	private void alterar_item(String id, Connection con, String nome_produto, String qtde)throws SQLException {
		ArrayList<String> idProduto = parcial(nome_produto, con);
		if(qtde.isEmpty()) {return;}
		String sql ="UPDATE controle SET qtde = "+qtde+" WHERE id_cartao = "+id+" AND id_produto = "+idProduto.get(2)+";";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
	}

	private ArrayList<String> parcial(String str1, Connection con)throws SQLException {
		String sql ="SELECT * FROM produtos WHERE nome_produto like '"+str1+"%';";
		PreparedStatement ps = con.prepareStatement(sql);
		ArrayList<String> busca = new ArrayList<String>();
		ResultSet res = ps.executeQuery();
		res.next();
		busca.add(res.getString("nome_produto"));
		busca.add(res.getString("valor"));
		busca.add(res.getString("id"));
		System.out.println("Banco parcial -> "+busca.get(0)+" -> "+busca.get(1)+" -> "+busca.get(2));
		return busca;
		
	}

	private void cadastro_cartao(String cartao_numero, Connection con)throws SQLException {
		String sql = "INSERT INTO cartao (numero) VALUES ("+cartao_numero+");";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.execute();
		this.execute(cartao_numero, "cartao_numero", "", "");
	}
	
	private ArrayList<String> consulta_cartao(String cartao_numero, Connection con)throws SQLException {
		String sql = "SELECT id, nome, telefone FROM cartao WHERE numero = "+cartao_numero+";";
		PreparedStatement ps = con.prepareStatement(sql);
		ArrayList<String> consulta_cartao = new ArrayList<String>();
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			consulta_cartao.add(res.getString("id"));
			consulta_cartao.add(res.getString("nome"));
			consulta_cartao.add(res.getString("telefone"));
		}
		return consulta_cartao;
	}

	private void limpar(String id, Connection con)throws SQLException {
		String sql = "UPDATE cartao SET nome = '0' WHERE id = "+id+";";
		String sql2 ="UPDATE cartao SET telefone = '0' WHERE id = "+id+";";
		String sql3 ="DELETE FROM controle WHERE id_cartao = "+id+";";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeQuery();
		ps = con.prepareStatement(sql2);
		ps.executeQuery();
		ps = con.prepareStatement(sql3);
		ps.executeQuery();
	}

	private ArrayList<String> consulta(String id,Connection con)throws SQLException {
		String sql ="SELECT cartao.id AS id_cartao, produtos.nome_produto AS produto, SUM(controle.qtde) AS QTDE, produtos.valor FROM controle " + 
					"LEFT JOIN cartao ON cartao.id = id_cartao " + 
					"LEFT JOIN produtos ON produtos.id = id_produto " + 
					"WHERE id_cartao = "+id+" GROUP BY produto;";
		PreparedStatement ps = con.prepareStatement(sql);
		ArrayList<String> consulta = new ArrayList<String>();
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			consulta.add(res.getString("produto")+" X "+res.getString("qtde")+" = R$"+(Float.parseFloat(res.getString("valor"))*(Float.parseFloat(res.getString("qtde")))));
			System.out.println("consulta banco -> "+consulta.get(res.getRow()-1));
		}
		return consulta;
	}

	private void add_pedido(String id, Connection con, String id_produto, String qtde )throws SQLException {
		String sqlFind = "SELECT id_cartao, id_produto, qtde FROM controle WHERE id_cartao = "+id+" AND id_produto = "+id_produto+";";
		PreparedStatement ps = con.prepareStatement(sqlFind);
		ResultSet find = ps.executeQuery();
		if(find.next()) {
			int bd_qtde = Integer.parseInt(find.getString("qtde")) + Integer.parseInt(qtde);
			String sql1 ="UPDATE controle SET qtde = "+bd_qtde+" WHERE id_cartao = "+id+" AND id_produto = "+id_produto+";";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.executeQuery();
		}else {
			String sql2 ="INSERT INTO controle (id_cartao, id_produto, qtde) VALUES ("+id+", "+id_produto+", "+qtde+");";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.executeQuery();
		}
	}

	private void add_nome(String id, Connection con, String nome, String telefone)throws SQLException {
		String sql ="UPDATE cartao SET nome = '"+nome+"' WHERE id = "+id+";"; 
		String sql2 ="UPDATE cartao SET telefone = '"+telefone+"' WHERE id = "+id+";";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeQuery();
		ps = con.prepareStatement(sql2);
		ps.executeQuery();
	}
}