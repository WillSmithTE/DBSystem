package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CharityListing {
    @Id
    @Column(name="charity_listing_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long charityListingID;

    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(name = "listing_title")
    private String listingTitle;

    @Column(name = "listing_description")
    private String listingDescription;

    @Column(name = "location")
    private String location;

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = new Date();
        }
    }


    public CharityListing() {
    }

    public CharityListing(Long charityListingID, Charity charity, Industry industry, String listingTitle, String listingDescription, String location, java.util.Date timestamp) {
        this.charityListingID = charityListingID;
        this.charity = charity;
        this.industry = industry;
        this.listingTitle = listingTitle;
        this.listingDescription = listingDescription;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Long getCharityListingID() {
        return this.charityListingID;
    }

    public void setCharityListingID(Long charityListingID) {
        this.charityListingID = charityListingID;
    }

    public Charity getCharity() {
        return this.charity;
    }

    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getListingTitle() {
        return this.listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getListingDescription() {
        return this.listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public java.util.Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public CharityListing charityListingID(Long charityListingID) {
        this.charityListingID = charityListingID;
        return this;
    }

    public CharityListing charity(Charity charity) {
        this.charity = charity;
        return this;
    }

    public CharityListing industry(Industry industry) {
        this.industry = industry;
        return this;
    }

    public CharityListing listingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
        return this;
    }

    public CharityListing listingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
        return this;
    }

    public CharityListing location(String location) {
        this.location = location;
        return this;
    }

    public CharityListing timestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}