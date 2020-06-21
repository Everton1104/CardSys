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
		case "apagar_item": {
			apagar_item(id, conexao, str1, str2);
			return null;
		}
		default:
			throw new IllegalArgumentException("Valor inesperado: " + opc);
		}
	}

	private void apagar_item(String id, Connection con, String str1, String str2)throws SQLException {
		String[] sp = str1.split("X");
		ArrayList<String>item = parcial(sp[0].trim(), con);
		System.out.println("Banco apagando item -> "+sp[0]);
		String sql ="DELETE FROM controle WHERE id_cartao = "+id+" AND id_produto = "+item.get(2)+";";
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
		
		String sql ="SELECT cartao.id AS id_cartao, produtos.nome_produto AS produto, controle.qtde, produtos.valor FROM controle "+ 
					"LEFT JOIN cartao ON cartao.id = id_cartao "+
					"LEFT JOIN produtos ON produtos.id = id_produto "+
					"WHERE id_cartao = "+id+";";
		PreparedStatement ps = con.prepareStatement(sql);
		ArrayList<String> consulta = new ArrayList<String>();
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			consulta.add(res.getString("produto")+" X "+res.getString("qtde")+" = R$"+(Float.parseFloat(res.getString("valor"))*(Float.parseFloat(res.getString("qtde")))));
			System.out.println("consulta banco -> "+consulta.get(res.getRow()-1));
		}
		return consulta;
	}

	private void add_pedido(String id, Connection con, String produto, String qtde )throws SQLException {
		String sql ="INSERT INTO controle (id_cartao, id_produto, qtde) VALUES ("+id+", "+produto+", "+qtde+");";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeQuery();
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


/*	
select * from usuarios where nomes like '%pedro%'

CREATE TABLE `cartao` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`numero` VARCHAR(8) NULL DEFAULT '0' COLLATE 'utf8_general_ci',
	`nome` VARCHAR(50) NULL DEFAULT 'sem_nome' COLLATE 'utf8_general_ci',
	`telefone` VARCHAR(50) NULL DEFAULT 'sem_telefone' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;



CREATE TABLE `controle` (
	`qtde` INT(10) UNSIGNED NULL DEFAULT '0',
	`id_cartao` INT(10) UNSIGNED NULL DEFAULT '0',
	`id_produto` INT(10) UNSIGNED NULL DEFAULT '0',
	INDEX `FK_controle_cartao` (`id_cartao`) USING BTREE,
	INDEX `FK_controle_produtos` (`id_produto`) USING BTREE,
	CONSTRAINT `FK_controle_cartao` FOREIGN KEY (`id_cartao`) REFERENCES `banco_cardsys`.`cartao` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_controle_produtos` FOREIGN KEY (`id_produto`) REFERENCES `banco_cardsys`.`produtos` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;



CREATE TABLE `produtos` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`nome_produto` VARCHAR(50) NULL DEFAULT '0' COLLATE 'utf8_general_ci',
	`valor` FLOAT(12) NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;


 */