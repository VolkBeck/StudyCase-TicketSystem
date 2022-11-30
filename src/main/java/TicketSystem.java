import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.List;
@Named
@ApplicationScoped
public class TicketSystem {

    //Attribute
    private TicketSystemDAO ticketSystemDAO;

    //Konstruktoren
    public TicketSystem(){
        ticketSystemDAO = new TicketSystemDAO();
    }

    //Methoden
    public List<Anforderung> getTickets() {
        return ticketSystemDAO.alleAnforderungen();
    }

    public List<Benutzer> getAlleBenutzer(){
        return ticketSystemDAO.alleBenutzer();
    }

    public List<Testfall> getTestfälleTester(Benutzer benutzer){
        return ticketSystemDAO.getTestfälleTester(benutzer);
    }

    public List<Testfall> getTestfälle(Anforderung anforderung){
        return ticketSystemDAO.getTestfälle(anforderung);
    }

    public List<Testlauf> getTestläufe(Testfall testfall){
        return ticketSystemDAO.getTestläufe(testfall);
    }

    public void neueAnforderung(Anforderung anforderung){
        ticketSystemDAO.neueAnforderung(anforderung);
    }

    public void anforderungAktuallisieren(Anforderung anforderung){
        ticketSystemDAO.anforderungAktuallisieren(anforderung);
    }

    public void neuerTestfallForAnforderung(Testfall testfall, Anforderung anforderung){
        ticketSystemDAO.neuerTestfallForAnforderung(testfall,anforderung);
    }

    public void neuerTestlaufForTestfall(Testlauf testlauf, Testfall testfall, Benutzer benutzer){
        ticketSystemDAO.neuerTestlaufForTestfall(testlauf,testfall,benutzer);
    }

    public void neuerBenutzer(Benutzer benutzer){
        ticketSystemDAO.neuerBenutzer(benutzer);
    }
}

