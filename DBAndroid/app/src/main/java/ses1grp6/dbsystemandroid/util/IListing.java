package ses1grp6.dbsystemandroid.util;

import java.util.Date;

import ses1grp6.dbsystemandroid.model.Charity;

public interface IListing {

    String getTitle();

    String getDescription();

    String getLocation();

    Date getDate();

    String getIndustry();

    Charity getCharity();

}
