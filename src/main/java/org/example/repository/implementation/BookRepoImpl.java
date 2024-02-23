package org.example.repository.implementation;


import org.example.model.Passenger;
import org.example.repository.BookRepo;
import org.example.service.BookService;
import org.example.service.implementaion.BookServiceImpl;

import java.util.*;

public class BookRepoImpl implements BookRepo {
    public static int availLowBerth=2;
    public static int availHighBerth=2;
    public static int availMidBerth=2;
    public static int availRac=2;
    public static int availWaitingList=2;

    public static Queue<Integer> RacQueue = new LinkedList<>();
    public static Queue<Integer> WaintingQueue = new LinkedList<>();
    public static List<Integer> BookingList = new ArrayList<>();

    public static List<Integer> lowBerthPosition = new ArrayList<>(Arrays.asList(1,2));
    public static List<Integer> HighBerthPosition = new ArrayList<>(Arrays.asList(1,2));
    public static List<Integer> MidBerthPosition = new ArrayList<>(Arrays.asList(1,2));
    public static List<Integer> RacPosition = new ArrayList<>(Arrays.asList(1,2));
    public static List<Integer> WaitingListPosition = new ArrayList<>(Arrays.asList(1,2));

    public Map<Integer, Passenger> Passengers= new HashMap<>();

    @Override
    public void bookTicket(Passenger p, int position, String berth) {
        p.number=position;
        p.alloted=berth;
        Passengers.put(p.passengerId,p);
        BookingList.add(p.passengerId);
        System.out.println(Passengers);
        System.out.println("************** Booking Successfull ******************");
    }

    @Override
    public void addToRAC(Passenger p, int position, String berth) {
        p.number=position;
        p.alloted=berth;
        Passengers.put(p.passengerId,p);
        RacQueue.add(p.passengerId);
        availRac--;
        RacPosition.remove(0);
        System.out.println("************** Booking Successfull to RAC ******************");
    }

    @Override
    public void addToWaiting(Passenger p, int position, String berth) {
        p.number=position;
        p.alloted=berth;
        Passengers.put(p.passengerId,p);
        WaintingQueue.add(p.passengerId);
        availWaitingList--;
        WaitingListPosition.remove(0);
        System.out.println("************** Booking Successfull to waitingList ******************");
    }

    public void cancel(int id){
        BookService bookService=new BookServiceImpl();
        Passenger p=Passengers.get(id);
        Passengers.remove(id);
        BookingList.remove(Integer.valueOf(id));
        int position=p.number;
        System.out.println("************** cancelled sucessfully ******************");
        if(p.alloted.equals("l")){
            availLowBerth++;
            lowBerthPosition.add(position);
        } else if(p.alloted.equals("m")){
            availMidBerth++;
            MidBerthPosition.add(position);
        } else if(p.alloted.equals("h")){
            availHighBerth++;
            HighBerthPosition.add(position);
        }

        if(RacQueue.size()>0){
            Passenger pasrac=Passengers.get(RacQueue.poll());
            int positionrac =pasrac.number;
            RacPosition.add(positionrac);
            RacPosition.remove(Integer.valueOf(pasrac.number));
            availRac++;
            if(WaintingQueue.size()>0){
                Passenger paswait=Passengers.get(WaintingQueue.poll());
                int positionwait =paswait.number;
                WaitingListPosition.add(positionwait);
                WaitingListPosition.remove(Integer.valueOf(positionwait));
                paswait.number=RacPosition.get(0);
                paswait.alloted="rac";
                RacPosition.remove(0);
                RacPosition.add(paswait.number);
                availWaitingList++;
                availRac--;
            }
            bookService.bookRail(pasrac);
        }
    }


    @Override
    public void printPassengers() {
        if (Passengers.isEmpty()) {
            System.out.println("No passengers have been booked.");
            return;
        }
        for (Map.Entry<Integer, Passenger> entry : Passengers.entrySet()) {
            Passenger passenger = entry.getValue();
            System.out.println("Passenger ID: " + passenger.passengerId);
            System.out.println("Name: " + passenger.name);
            System.out.println("Age: " + passenger.age);
            System.out.println("Berth: " + passenger.berth);
            System.out.println("Allotted Berth: " + passenger.alloted);
            System.out.println("Position: " + passenger.number);
            System.out.println("------------------------");
        }
    }

}
