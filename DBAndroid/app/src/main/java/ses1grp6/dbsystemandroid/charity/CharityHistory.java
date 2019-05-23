package ses1grp6.dbsystemandroid.charity;

import java.io.Serializable;
import java.util.Calendar;

public class CharityHistory implements Serializable {

    public final String title;
    public final Calendar time;
    public final String address;
    public final String description;

    public CharityHistory(String title, Calendar time, String address, String description) {
        this.title = title;
        this.time = time;
        this.address = address;
        this.description = description;
    }
}
