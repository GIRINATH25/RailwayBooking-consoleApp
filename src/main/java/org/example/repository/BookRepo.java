package org.example.repository;

import org.example.model.Passenger;

import java.util.*;

public interface BookRepo {
    public void bookTicket(Passenger p,int position,String berth);
    public void addToRAC(Passenger p,int position,String berth);
    public void addToWaiting(Passenger p,int position,String berth);
    void cancel(int id);
    void printPassengers();
}
