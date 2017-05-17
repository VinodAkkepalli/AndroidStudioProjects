package com.shine.practice.model;

/**
 * Created by Vinod Akkepalli on 5/17/2017.
 */

public class Motor {

    private int rpm;

    public Motor(){
        this.rpm = 0;
    }

    public int getRpm(){
        return rpm;
    }

    public void accelerate(int value){
        rpm = rpm + value;
    }

    public void brake(){
        rpm = 0;
    }
}
