package ses1grp6.dbsystemandroid.donor;

import java.io.Serializable;
import java.util.Date;

public class DonorHistory implements Serializable {

    public final String title;
    public final Date date;
    public final String charityName;
    public final String address;
    public final String description;

    public DonorHistory(String title, Date date, String charityName, String address, String description) {
        this.title = title;
        this.date = date;
        this.charityName = charityName;
        this.address = address;
        this.description = description;
    }
}
