package ses1grp6.dbsystemandroid.util;

import org.json.JSONObject;

import java.util.Date;

import ses1grp6.dbsystemandroid.charity.model.Charity;

public interface IListing {

    String getTitle();

    String getDescription();

    String getLocation();

    Date getDate();

    String getIndustry();

    Charity getCharity();

}
