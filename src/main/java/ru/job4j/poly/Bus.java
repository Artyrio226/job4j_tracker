package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Bus drives!");
    }

    @Override
    public void setPassengers(int passengers) {
        System.out.println(passengers + " passengers on the bus!");
    }

    @Override
    public double refuel(int fuel) {
        double price = 4.7;
        return price * fuel;
    }
}
