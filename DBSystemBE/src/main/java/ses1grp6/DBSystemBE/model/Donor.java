package ses1grp6.DBSystemBE.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Donor extends User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long donorId;

    public Donor(Long donorId) {
        this.donorId = donorId;
    }


    public Donor() {
    }

    public Donor(RegistrationRequest registrationRequest) {
        this();
        assignRegisterProperties(registrationRequest);
    }


    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public Long getId() {
        return getDonorId();
    }
}
