package org.example.service;

import org.example.model.Passenger;

public interface BookService {
    void bookRail(Passenger p);
    void cancelBook(int id);
    void printAvailable();
    void printPassengers();

}
