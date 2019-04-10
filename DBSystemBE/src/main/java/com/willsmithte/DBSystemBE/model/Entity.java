package com.willsmithte.DBSystemBE.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by Will Smith on 4/4/19.
 */

@MappedSuperclass
public class Entity {

    private String name;
    @Id
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private int locationId;
    private String description;
    private boolean emailConfirmed = false;

    public Entity() {}

    public Entity(String name, String email, String password, int locationId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void confirmEmail() {
        this.emailConfirmed = true;
    }
}
