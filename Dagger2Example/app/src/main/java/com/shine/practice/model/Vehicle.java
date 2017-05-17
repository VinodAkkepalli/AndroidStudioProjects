package com.shine.practice.model;

import javax.inject.Inject;

/**
 * Created by Vinod Akkepalli on 5/17/2017.
 */

public class Vehicle {

    private Motor motor;

    //@Inject
    public Vehicle(Motor motor){
        this.motor = motor;
    }

    public void increaseSpeed(int value){
        motor.accelerate(value);
    }

    public void stop(){
        motor.brake();
    }

    public int getSpeed(){
        return motor.getRpm();
    }
}
