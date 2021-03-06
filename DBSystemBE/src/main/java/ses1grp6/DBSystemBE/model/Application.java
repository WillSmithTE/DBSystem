package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Application {

    @Id
    @Column(name="application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "cover_letter")
    private String coverLetter;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name= "accepted")
    private Integer accepted;

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = new Date();
        }
        if (this.accepted == null) {
            this.accepted = 0;
        }
    }

    public Application() {

    }

    public Application(Long applicationID, Donor donor, Charity charity, CharityListing charityListing, String coverLetter, String contactNumber, Integer accepted, java.util.Date timestamp) {
        this.applicationID = applicationID;
        this.donor = donor;
        this.charity = charity;
        this.charityListing = charityListing;
        this.coverLetter = coverLetter;
        this.contactNumber = contactNumber;
        this.accepted = 0;
        this.timestamp = timestamp;
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

    public Integer getAccepted() {
        return this.accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public java.util.Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
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

    public Application coverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
        return this;
    }

    public Application contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public Application accepted(Integer accepted) {
        this.accepted = accepted;
        return this;
    }

    public Application timestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void reject() {
        this.setAccepted(2);
    }

    public void accept() {
        this.setAccepted(1);
    }

}
