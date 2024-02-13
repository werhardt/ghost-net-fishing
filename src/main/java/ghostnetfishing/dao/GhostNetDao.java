package ghostnetfishing.dao;

import java.util.List;

import ghostnetfishing.entities.GhostNet;
import ghostnetfishing.entities.Person;
import ghostnetfishing.helper.AppConstants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class GhostNetDao {

	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetfishing-persistenceunit");
	
	public static void save(GhostNet net) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		em.persist(net);
		t.commit();
		
		em.close();
	}
	
	public static void update(GhostNet net) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		em.merge(net);
		t.commit();
		
		em.close();
	}
	
	public static List<GhostNet> findBySalvagerId(int salvagerId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select g from GhostNet g where g.salvager.id = ?1");
		query.setParameter(1, salvagerId);
		return query.getResultList();
	}
	
	public static List<GhostNet> findByStatus(int status) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select g from GhostNet g where g.status = ?1");
		query.setParameter(1, status);
		return query.getResultList();
	}
	
	public static List<GhostNet> findPendingNets(int salvagerId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select g from GhostNet g where g.status = ?1 and g.salvager != null and g.salvager.id != ?2");
		query.setParameter(1, AppConstants.GHOSTNET_STATUS_PENDING);
		query.setParameter(2, salvagerId);
		return query.getResultList();
	}
	
	public static GhostNet findById(int netId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select g from GhostNet g where g.id = ?1");
		query.setParameter(1, netId);
		List<GhostNet> result = query.getResultList();
		if(result.size() == 1) {
			return result.get(0);
		}
		return null;
	}
}
