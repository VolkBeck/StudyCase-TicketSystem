import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class TicketSystemDAO {

    //Attribute
    private EntityManagerFactory emf = createEntityManagerFactory("ticketsystem");

    //Konstruktoren
    public TicketSystemDAO(){}

    //Methoden
    public List<Anforderung> alleAnforderungen(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Anforderung a");
        List<Anforderung> tickets = q.getResultList();
        em.close();

        return tickets;
    }

    public void neueAnforderung(Anforderung anforderung){
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(anforderung);
        t.commit();
        em.close();
    }

    public void anforderungAktuallisieren(Anforderung anforderung){
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(anforderung);
        t.commit();
        em.close();
    }

    public List<Benutzer> alleBenutzer(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Benutzer a");
        List<Benutzer> alleBenutzer = q.getResultList();

        return alleBenutzer;
    }

    public void neuerBenutzer(Benutzer benutzer){
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(benutzer);
        t.commit();
        em.close();
    }

    public List<Testfall> getTestfälleTester(Benutzer benutzer){
        int i = benutzer.getNummer();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select usertestfälle from Benutzer where nummer=" + i);
        List<Testfall> testfälleTester = q.getResultList();
        em.close();

        return testfälleTester;
    }

    public List<Testfall> getTestfälle(Anforderung anforderung){
        long i = anforderung.getId();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select testfälle from Anforderung where id= " + i);
        List<Testfall> testfälle = q.getResultList();
        em.close();

        return testfälle;
    }

    public void neuerTestfallForAnforderung(Testfall testfall, Anforderung anforderung){
        testfall.setAnforderung(anforderung);
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(testfall);
        em.merge(anforderung);
        t.commit();
        em.close();
    }

    public List<Testlauf> getTestläufe(Testfall testfall){
        int i = testfall.getTestfallNummer();
        EntityManager em = emf.createEntityManager();
        Query q =em.createQuery("select testläufe from Testfall where testfallNummer=" + i);
        List<Testlauf> testläufe = q.getResultList();
        em.close();

        return testläufe;
    }

    public void neuerTestlaufForTestfall(Testlauf testlauf, Testfall testfall, Benutzer benutzer){
        testfall.setTester(benutzer);
        testlauf.setTestfall(testfall);
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(testfall);
        em.merge(testlauf);
        t.commit();
        em.close();
    }
}
