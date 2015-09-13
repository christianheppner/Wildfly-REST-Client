import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Benutzer;

@RequestScoped
@Path("/benutzer")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RestBenutzer {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;

	public RestBenutzer() {
		this.emf = Persistence.createEntityManagerFactory("jcg-JPA");
		this.em = emf.createEntityManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Benutzer> GetBenutzer() 
	{
		Collection<Benutzer> benutzer = this.findAll();
		return benutzer;
	}

	@DELETE
	@Path("/{benutzerId}")
	public void DeleteBenutzer(@PathParam("benutzerId") String benutzerId) 
	{
		Benutzer employee = em.find(Benutzer.class, Long.parseLong(benutzerId));
		tx = em.getTransaction();
		em.getTransaction().begin();
		em.remove(employee);
		em.getTransaction().commit();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateBenutzer(Benutzer benutzer) 
	{
		tx = em.getTransaction();
		tx.begin();
		em.persist(benutzer);
		tx.commit();
	}

	@PUT
	@Path("/{benutzerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void UpdateBenutzer(@PathParam("benutzerId") String benutzerId, Benutzer benutzer) 
	{
		Benutzer employee = em.find(Benutzer.class, Long.parseLong(benutzerId));
		employee.setVorname(benutzer.getVorname());
		tx = em.getTransaction();
		tx.begin();
		em.persist(employee);
		tx.commit();
	}

	private Collection<Benutzer> findAll() 
	{
		Query query = this.em.createQuery("SELECT c FROM Benutzer c");
		Collection<Benutzer> resultList = (Collection<Benutzer>) query.getResultList();
		return resultList;
	}

}
