package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;
import javax.persistence.ManyToOne;

@Entity
public class Application {

    @Id
    @Column(name="application_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationID;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @ManyToOne
    @JoinColumn(name = "charity_listing_id")
    private CharityListing charityListing;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "contact_number")
    private String contactNumber;

    public Application() {
    }


    public Application(Long applicationID, Donor donor, Charity charity, CharityListing charityListing, Industry industry, String coverLetter, String contactNumber) {
        this.applicationID = applicationID;
        this.donor = donor;
        this.charity = charity;
        this.charityListing = charityListing;
        this.industry = industry;
        this.coverLetter = coverLetter;
        this.contactNumber = contactNumber;
    }

    public Long getApplicationID() {
        return this.applicationID;
    }

    public void setApplicationID(Long applicationID) {
        this.applicationID = applicationID;
    }

    public Donor getDonor() {
        return this.donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Charity getCharity() {
        return this.charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public CharityListing getCharityListing() {
        return this.charityListing;
    }

    public void setCharityListing(CharityListing charityListing) {
        this.charityListing = charityListing;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getCoverLetter() {
        return this.coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Application applicationID(Long applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public Application donor(Donor donor) {
        this.donor = donor;
        return this;
    }

    public Application charity(Charity charity) {
        this.charity = charity;
        return this;
    }

    public Application charityListing(CharityListing charityListing) {
        this.charityListing = charityListing;
        return this;
    }

    public Application industry(Industry industry) {
        this.industry = industry;
        return this;
    }

    public Application coverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
        return this;
    }

    public Application contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Application)) {
            return false;
        }
        Application application = (Application) o;
        return Objects.equals(applicationID, application.applicationID) && Objects.equals(donor, application.donor) && Objects.equals(charity, application.charity) && Objects.equals(charityListing, application.charityListing) && Objects.equals(industry, application.industry) && Objects.equals(coverLetter, application.coverLetter) && Objects.equals(contactNumber, application.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationID, donor, charity, charityListing, industry, coverLetter, contactNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " applicationID='" + getApplicationID() + "'" +
            ", donor='" + getDonor() + "'" +
            ", charity='" + getCharity() + "'" +
            ", charityListing='" + getCharityListing() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", coverLetter='" + getCoverLetter() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            "}";
    }


}
