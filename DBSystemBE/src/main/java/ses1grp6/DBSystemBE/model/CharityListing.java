package ses1grp6.DBSystemBE.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CharityListing {
    @Id
    @Column(name="charity_listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "charity_id")
    private Charity charity;

    @Column(name = "listing_title")
    private String listingTitle;

    @Column(name = "listing_description")
    @org.hibernate.annotations.Type( type = "text" )
    private String listingDescription;

    @Column(name = "location")
    private String location;

    @Basic
    @Column(name="created_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    @Basic
    @Column(name="expires_at", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date expiresAt;

    @Basic
    @Column(name="event_start_date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date eventStartDate;

    @Basic
    @Column(name="event_end_date", updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date eventEndDate;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }

    public CharityListing() {
    }

    public CharityListing(Integer id) {
        this.id = id;
    }

    public CharityListing(Integer id, Charity charity, Industry industry, String listingTitle, String listingDescription, String location, java.util.Date createdAt, java.util.Date expiresAt, java.util.Date eventStartDate, java.util.Date eventEndDate) {
        this.id = id;
        this.charity = charity;
        this.industry = industry;
        this.listingTitle = listingTitle;
        this.listingDescription = listingDescription;
        this.location = location;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public java.util.Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public java.util.Date getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(java.util.Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public java.util.Date getEventStartDate() {
        return this.eventStartDate;
    }

    public void setEventStartDate(java.util.Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public java.util.Date getEventEndDate() {
        return this.eventEndDate;
    }

    public void setEventEndDate(java.util.Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public CharityListing id(Integer id) {
        this.id = id;
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

    public CharityListing createdAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CharityListing expiresAt(java.util.Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public CharityListing eventStartDate(java.util.Date eventStartDate) {
        this.eventStartDate = eventStartDate;
        return this;
    }

    public CharityListing eventEndDate(java.util.Date eventEndDate) {
        this.eventEndDate = eventEndDate;
        return this;
    }
}