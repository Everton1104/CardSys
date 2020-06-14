package br.com.CardSys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Banco {
	public ArrayList<String> execute(String id) throws SQLException {

		String url = "jdbc:mysql://127.0.0.1/banco_cardsys";
		String user = "root";
		String password = "root";

		Connection conexao = DriverManager.getConnection(url, user, password);
		
		ArrayList<String> cliente = new ArrayList<String>();
		
		
		String sql = "SELECT clientes.nome, clientes.telefone, cartao.numero FROM clientes LEFT JOIN cartao ON clientes.id_cartao = cartao.id WHERE cartao.numero = "+id+";";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet res = ps.executeQuery();
		while(res.next()) {
			cliente.add(res.getString("nome"));
			cliente.add(res.getString("telefone"));
			cliente.add(res.getString("numero"));
		}
		return cliente;
	}
}


/*



 *CRIACAO TABELA CARTAO*
 CREATE TABLE `cartao` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`numero` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

*CRIACAO TABELA CLIENTE*
CREATE TABLE `clientes` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`telefone` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`id_cartao` INT(10) UNSIGNED NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `FK_clientes_cartao` (`id_cartao`) USING BTREE,
	CONSTRAINT `FK_clientes_cartao` FOREIGN KEY (`id_cartao`) REFERENCES `banco_cardsys`.`cartao` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;


 */