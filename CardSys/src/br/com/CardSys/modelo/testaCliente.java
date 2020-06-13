package br.com.CardSys.modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testaCliente {
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setNome("everton");
		c.setTelefone("12345");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cliente");
		EntityManager em = emf.createEntityManager();
		emf.close();
		
	}
}
