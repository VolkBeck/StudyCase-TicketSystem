import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TicketController implements Serializable {

    //Attribute
    private Anforderung anforderung = null;
    private Testfall testfall = null;
    private Testlauf testlauf = null;
    private Benutzer benutzer = null;
    private  Benutzer tester = null;
    private Benutzer tmpBenutzer = null;
    private RollenRender rollenRender = new RollenRender();
    @Inject
    private TicketSystem ticketSystem;

    //Konstruktoren
    public TicketController(){};

    //Methoden
    public void welchesTicket(SelectEvent<Object> event) {
        PrimeFaces current = PrimeFaces.current();
        try {
            if (event.getObject() instanceof Anforderung) {
                this.anforderung = (Anforderung) event.getObject();
                if (this.benutzer.getRolle().equals("Requirements Engineer")) {
                    current.executeScript("PF('anforderungakt').show();");
                }
            } else if (event.getObject() instanceof Testfall) {
                this.testfall = (Testfall) event.getObject();
                this.anforderung = this.testfall.getAnforderung();
                current.executeScript("PF('neuertestfall').show();");
            }else if (event.getObject() instanceof Testlauf){
                this.testlauf = (Testlauf) event.getObject();
                if (this.benutzer.getRolle().equals("Testmanager")){
                    current.executeScript("PF('neuertestlauf').show()");
                }
            }
        } catch (ClassCastException e) {
            System.out.println("Beim Casten ist folgender Fehler aufgetreten " + e);
        }
    }
    public void login() {
        if (this.benutzer != null) {
            List<Benutzer> alleBenutzer = ticketSystem.getAlleBenutzer();
            for (Benutzer benutzer : alleBenutzer) {
                if (benutzer.equals(tmpBenutzer)) {
                    this.benutzer = benutzer;
                    if (this.benutzer.getRolle().equals("Requirements Engineer")) {
                        try {
                            rollenRender.render(this.benutzer);
                            rollenRender.isRequirementsEngineer();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                        } catch (IOException e) {}
                    } else if (this.benutzer.getRolle().equals("Testfallersteller")) {
                        try {
                            rollenRender.render(this.benutzer);
                            rollenRender.isTestfallErsteller();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                        } catch (IOException e) {}
                    } else if (this.benutzer.getRolle().equals("Testmanager")) {
                        try {
                            rollenRender.render(this.benutzer);
                            rollenRender.isTestManager();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                        } catch (IOException e) {}
                    }else if (this.benutzer.getRolle().equals("Tester")) {
                        try {
                            rollenRender.render(this.benutzer);
                            rollenRender.isTester();
                            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                        } catch (IOException e) {}
                    }
                }
            }
        }
    }
    public void anforderungHinzufügen() {
        ticketSystem.neueAnforderung(this.anforderung);
        this.anforderung = null;
    }
    public void anforderungAktuallisieren() {
        ticketSystem.anforderungAktuallisieren(this.anforderung);
        this.anforderung = null;
    }
    public void testfallHinzufügen() {
        ticketSystem.neuerTestfallForAnforderung(this.testfall, this.anforderung);
        this.testfall = null;
        this.tester = null;
    }
    public void testlaufHinzufügen(){
        for (Benutzer benutzer: getAlleBenutzer()){
            if (benutzer.getVorName().equals(this.tester.getVorName())){
                this.tester = benutzer;
            }
        }
        ticketSystem.neuerTestlaufForTestfall(this.testlauf, this.testfall, this.tester);
        this.testlauf = null;
    }
    public void benutzerHinzufügen() {
        ticketSystem.neuerBenutzer(this.benutzer);
        this.benutzer = null;
    }

    public List<String> tester(){
        List<String> tester = new ArrayList<>();
        for (Benutzer benutzer: getAlleBenutzer()) {
            if (benutzer.getRolle().equals("Tester"))
                tester.add(benutzer.getVorName());
        }
        return tester;
    }

    //Getter und Setter Methoden
    public List<Anforderung>getTickets() {
        return ticketSystem.getTickets();
    }
    public List<Testfall> getTestfälle() {
        return ticketSystem.getTestfälle(this.anforderung);
    }
    public List<Testlauf> getTestläufe(){
        return ticketSystem.getTestläufe(this.testfall);
    }
    public List<Testfall> getTesterAufgaben(){
        return ticketSystem.getTestfälleTester(this.benutzer);
    }
    public List<Benutzer> getAlleBenutzer(){return ticketSystem.getAlleBenutzer();}

    public Anforderung getAnforderung() {
        if (this.anforderung == null) {
            this.anforderung = new Anforderung();
        }
        return this.anforderung;
    }
    public void setAnforderung(Anforderung anforderung) {
        this.anforderung = anforderung;
    }

    public Testlauf getTestlauf() {

        if (null == this.testlauf) {
            this.testlauf = new Testlauf();
        }
        return this.testlauf;
    }
    public void setTestlauf(Testlauf testlauf) {
        this.testlauf = testlauf;
    }

    public Testfall getTestfall() {
        if (this.testfall == null) {
            this.testfall = new Testfall();
        }
        return this.testfall;
    }
    public void setTestfall(Testfall testfall) {
        this.testfall = testfall;
    }

    public Benutzer getBenutzer() {
        if (this.benutzer == null) {
            this.benutzer = new Benutzer();
        }
        return this.benutzer;
    }
    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Benutzer getTester() {
        if (this.tester == null){
            this.tester = new Benutzer();
        }
        return this.tester;
    }
    public void setTester(Benutzer tester) {
        for (Benutzer benutzer: getAlleBenutzer()) {
            if (benutzer.equals(tester.getVorName())){
                this.tester = benutzer;
            }

        }
        System.out.println("Der Tester wurde nicht gesetzt");
    }

    public Benutzer getTmpBenutzer() {
        if (this.tmpBenutzer == null) {
            this.tmpBenutzer = new Benutzer();
        }
        return tmpBenutzer;
    }
    public void setTmpBenutzer(Benutzer tmpBenutzer) {
        this.tmpBenutzer = tmpBenutzer;
    }

    public RollenRender getRollenRender() {
        return rollenRender;
    }
    public void setRollenRender(RollenRender rollenRender) {
        this.rollenRender = rollenRender;
    }
}

