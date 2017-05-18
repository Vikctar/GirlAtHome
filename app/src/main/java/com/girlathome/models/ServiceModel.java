package com.girlathome.models;

import java.io.Serializable;

/**
 * Created by steve on 5/16/17.
 */

public class ServiceModel implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
