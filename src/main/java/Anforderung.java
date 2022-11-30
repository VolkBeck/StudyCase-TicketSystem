import jakarta.persistence.*;
import java.util.List;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

@Entity
public class Anforderung {

    //Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    private String beschreibung;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anforderung")
    private List<Testfall> testfälle;

    //Konstruktoren
    public Anforderung(){}

    //Getter und Setter Methoden
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public List<Testfall> getTestfälle() {
        return testfälle;
    }
    public void setTestfälle(List<Testfall> testfälle) {
        this.testfälle = testfälle;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
