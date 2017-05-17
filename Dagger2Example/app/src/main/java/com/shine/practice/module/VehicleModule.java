package com.shine.practice.module;

import com.shine.practice.model.Motor;
import com.shine.practice.model.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vinod Akkepalli on 5/17/2017.
 */

@Module
public class VehicleModule {
    @Provides @Singleton
    Motor provideMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Vehicle provideVehicle(){
        return new Vehicle(new Motor());
    }
}
