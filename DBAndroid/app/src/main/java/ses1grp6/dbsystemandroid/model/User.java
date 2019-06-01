package ses1grp6.dbsystemandroid.model;

import android.content.Intent;

import java.util.Date;

public abstract class User {


    public abstract void putToIntent(Intent intent);

    public abstract boolean hasName();

    public abstract boolean hasContactNumber();

    public abstract boolean hasCreatedAt();

    public abstract void setName(String name);

    public abstract void setContactNumber(String contactNumber);

    public abstract void setCreatedAt(Date createdAt);

    public abstract void setCreatedAt(String s);

    public abstract int getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getContactNumber();

    public abstract Date getCreatedAt();

    public abstract String getFormattedCreatedAt();
}
