package ses1grp6.dbsystemandroid.charity;

import java.io.Serializable;
import java.util.Date;

public class CharityHistory implements Serializable {

    public final String title;
    public final Date date;
    public final String address;
    public final String description;

    public CharityHistory(String title, Date date, String address, String description) {
        this.title = title;
        this.date = date;
        this.address = address;
        this.description = description;
    }
}
