package org.example.model;

public class Passenger {
    static int id=1;
    public String name;
    public int age;
    public String berth;
    public int passengerId;
    public String alloted;
    public int number;

    public Passenger(String name, int age, String berth) {
        this.name = name;
        this.age = age;
        this.berth = berth;
        this.passengerId = id++;
        this.alloted = "";
        this.number = -1;
    }
}
