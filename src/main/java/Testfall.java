import jakarta.persistence.*;
import org.apache.derby.client.am.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Testfall{

    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int testfallNummer;
    private String testfallName;
    private String ergebnis;
    @ManyToOne
    private Anforderung anforderung;
    @ManyToOne
    private Benutzer tester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testfall")
    private List<Testlauf> testläufe;

    //Konstruktoren
    public Testfall(){}

    //Getter und Setter Methoden
    public int getTestfallNummer() {
        return testfallNummer;
    }
    public void setTestfallNummer(int testfallNummer) {
        this.testfallNummer = testfallNummer;
    }

    public String getTestfallName() {
        return testfallName;
    }
    public void setTestfallName(String testfallName) {
        this.testfallName = testfallName;
    }

    public String getErgebnis() {
        return ergebnis;
    }
    public void setErgebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }

    public Anforderung getAnforderung() {

        return anforderung;
    }
    public void setAnforderung(Anforderung anforderung) {
        this.anforderung = anforderung;
    }

    public List<Testlauf> getTestläufe() {

        return testläufe;
    }
    public void setTestläufe(List<Testlauf> testläufe) {
        this.testläufe = testläufe;
    }

    public Benutzer getTester() {
        return tester;
    }
    public void setTester(Benutzer tester) {
        this.tester = tester;
    }
}
