package ghostnetfishing.dao;

import java.util.List;

import ghostnetfishing.entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class PersonDao {

	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetfishing-persistenceunit");
	
	public static void save(Person person) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		em.persist(person);
		t.commit();
		
		em.close();
	}
	
	/**
	 * Suche nach einer Person anhand des Logins und des Passworts. Wenn die Daten nicht übereinstimmen,
	 * wird <code>null</code> zurückgegeben.
	 * @param login Der Login der Person.
	 * @param password Das Passwort der Person.
	 * @return Die gefundene Person oder <code>null</code>.
	 */
	public static Person findByLoginAndPassword(String login, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p from Person p where p.login = ?1 and p.password = ?2");
		query.setParameter(1, login);
		query.setParameter(2, password);
		List<Person> result = query.getResultList();
		if(result.size() == 1) {
			return result.get(0);
		}
		return null;
	}
	
	public static List<Person> findAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p from Person p");
		return query.getResultList();
	}
	
	public static Person findById(int id) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p from Person p where p.id = ?1");
		query.setParameter(1, id);
		List<Person> result = query.getResultList();
		if(result.size() == 1) {
			return result.get(0);
		}
		return null;
	}
}
