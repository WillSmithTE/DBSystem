package ses1grp6.dbsystemandroid.donor;

import java.io.Serializable;
import java.util.Calendar;

public class DonorHistory implements Serializable {

    public final String title;
    public final Calendar time;
    public final String charityName;
    public final String address;
    public final String description;

    public DonorHistory(String title, Calendar time, String charityName, String address, String description) {
        this.title = title;
        this.time = time;
        this.charityName = charityName;
        this.address = address;
        this.description = description;
    }
}
