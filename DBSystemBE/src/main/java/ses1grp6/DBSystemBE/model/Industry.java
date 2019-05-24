package ses1grp6.DBSystemBE.model;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Industry {

   @Id
   @Column(name="industry_id")
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long industryID;

   @Column(name = "industry_name")
   private String industryName;

   public Industry() {
   }

    public Industry(Long industryID, String industryName) {
        this.industryID = industryID;
        this.industryName = industryName;
    }

    public Long getIndustryID() {
        return this.industryID;
    }

    public void setIndustryID(Long industryID) {
        this.industryID = industryID;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Industry industryID(Long industryID) {
        this.industryID = industryID;
        return this;
    }

    public Industry industryName(String industryName) {
        this.industryName = industryName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Industry)) {
            return false;
        }
        Industry industry = (Industry) o;
        return Objects.equals(industryID, industry.industryID) && Objects.equals(industryName, industry.industryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(industryID, industryName);
    }

    @Override
    public String toString() {
        return "{" +
            " industryID='" + getIndustryID() + "'" +
            ", industryName='" + getIndustryName() + "'" +
            "}";
    }



}