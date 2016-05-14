package com.directions.entities;

/**
 * Created by vaibhav on 15/5/16.
 */
public class CustomerDetails extends AbstractPerson {

    private boolean isAvailableToReceive = true;

    public boolean isAvailableToReceive() {

        return isAvailableToReceive;
    }

    public void setAvailableToReceive(boolean availableToReceive) {

        isAvailableToReceive = availableToReceive;
    }

}
