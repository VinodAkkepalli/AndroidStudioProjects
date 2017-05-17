package com.shine.practice.component;

import com.shine.practice.model.Vehicle;
import com.shine.practice.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vinod Akkepalli on 5/17/2017.
 */

@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {

    Vehicle provideVehicle();
}
