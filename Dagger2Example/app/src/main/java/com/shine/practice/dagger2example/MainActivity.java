package com.shine.practice.dagger2example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.shine.practice.component.DaggerVehicleComponent;
import com.shine.practice.component.VehicleComponent;
import com.shine.practice.model.Motor;
import com.shine.practice.model.Vehicle;
import com.shine.practice.module.VehicleModule;

import javax.inject.Inject;

import dagger.Component;

public class MainActivity extends AppCompatActivity {
    @Inject
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*//without using '@Inject' for Vehicle, and instantiating explicitly as below
        //works just as fine
        vehicle = new Vehicle(new Motor());*/

        //Dagger magic
        DaggerVehicleComponent.create().inject(this);

        vehicle.increaseSpeed(10);
        Toast.makeText(getApplicationContext(),String.valueOf(vehicle.getSpeed()), Toast.LENGTH_LONG).show();
    }
}
