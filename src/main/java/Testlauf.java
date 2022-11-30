import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Testlauf{

    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int testlaufNummer;
    private String testlaufName;
    @Temporal(TemporalType.DATE)
    private Date testlaufDatum;
    @ManyToOne
    private Testfall testfall;

    //Konstruktoren
    public Testlauf(){}

    //Getter und Setter
    public String getTestlaufName() {
            return testlaufName;
        }
    public void setTestlaufName(String testlaufName) {
            this.testlaufName = testlaufName;
        }

    public int getTestlaufNummer() {
                return testlaufNummer;
            }
    public void setTestlaufNummer(int testlaufNummer) {
            this.testlaufNummer = testlaufNummer;
        }

    public Date getTestlaufDatum() {
            return testlaufDatum;
        }
    public void setTestlaufDatum(Date testlaufDatum) {
            this.testlaufDatum = testlaufDatum;
        }

    public Testfall getTestfall() {
            return testfall;
        }
    public void setTestfall(Testfall testfall) {
            this.testfall = testfall;
        }
    }

