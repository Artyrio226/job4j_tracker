package ru.job4j.poly;

public interface Transport {
    void drive();

    void setPassengers(int passengers);

    double refuel(int fuel);
}
