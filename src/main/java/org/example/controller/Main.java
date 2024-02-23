package org.example.controller;

import java.util.*;
import org.example.model.Passenger;
import org.example.repository.BookRepo;
import org.example.repository.implementation.BookRepoImpl;
import org.example.service.BookService;
import org.example.service.implementaion.BookServiceImpl;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BookService bookService=new BookServiceImpl();
        BookRepo bookRepo=new BookRepoImpl();
        boolean loop=true;
        while(loop){
            System.out.println("1.Booking\n2.Display Booked Ticket\n3.Available Ticket\n4.cancel Ticket\n5.close");
            int ch=scanner.nextInt();
            switch (ch){
                case 1:{
                    System.out.println("Enter the name:");
                    String name = scanner.next();
                    System.out.println("Enter the age:");
                    int age = scanner.nextInt();
                    System.out.println("Enter the berth:");
                    String berth = scanner.next();
                    Passenger p = new Passenger(name, age, berth);
                    bookService.bookRail(p);
                }
                break;
                case 2:{
                    bookService.printPassengers();
                }
                break;
                case 3:{
                    bookService.printAvailable();
                }
                break;
                case 4:{
                    System.out.println("Enter Passenger id to cancel:");
                    int id=scanner.nextInt();
                    bookService.cancelBook(id);
                }
                break;
                case 5:{
                    loop=false;
                }
                break;
                default:
                    break;
            }
        }
    }
}