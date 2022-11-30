import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import static jakarta.persistence.Persistence.createEntityManagerFactory;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Benutzer{

    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nummer;
    private String vorName;
    private String nachname;
    private String passwort;
    private String rolle;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tester")
    private List<Testfall> usertestfälle;

    //Konstruktoren
    public Benutzer(){}

    // Getter und Setter Methoden
    public String getVorName() {
        return vorName;
    }
    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getRolle() {
        return rolle;
    }
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getNummer() {
        return nummer;
    }
    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Testfall> getUsertestfälle() {
        return usertestfälle;
    }
    public void setUsertestfälle(List<Testfall> usertestfälle) {
        this.usertestfälle = usertestfälle;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benutzer benutzer = (Benutzer) o;
        return email.equals(benutzer.email) && passwort.equals(benutzer.passwort);
    }
    @Override
    public int hashCode() {
        return Objects.hash(vorName, nachname, passwort);
    }
}
