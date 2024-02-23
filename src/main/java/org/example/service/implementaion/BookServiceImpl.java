package org.example.service.implementaion;

import org.example.model.Passenger;
import org.example.repository.BookRepo;
import org.example.repository.implementation.BookRepoImpl;
import org.example.service.BookService;
public class BookServiceImpl implements BookService{

    BookRepo bookRepo=new BookRepoImpl();
    @Override
    public void bookRail(Passenger p) {
        if(BookRepoImpl.availWaitingList==0){
            System.out.println("No ticket available");
            return;
        }

        if((p.berth.equals("l") && BookRepoImpl.availLowBerth>0)||
                (p.berth.equals("m") && BookRepoImpl.availMidBerth>0)||
                (p.berth.equals("h") && BookRepoImpl.availHighBerth>0)){
            System.out.println("Your berth available");
            if(p.berth.equals("l")){
                bookRepo.bookTicket(p,BookRepoImpl.lowBerthPosition.get(0),"l");
                BookRepoImpl.lowBerthPosition.remove(0);
                BookRepoImpl.availLowBerth--;
            }else if(p.berth.equals("m")){
                bookRepo.bookTicket(p,BookRepoImpl.MidBerthPosition.get(0),"m");
                BookRepoImpl.MidBerthPosition.remove(0);
                BookRepoImpl.availMidBerth--;
            }else if(p.berth.equals("h")){
                bookRepo.bookTicket(p,BookRepoImpl.HighBerthPosition.get(0),"h");
                BookRepoImpl.HighBerthPosition.remove(0);
                BookRepoImpl.availHighBerth--;
            }
        }else if(BookRepoImpl.availLowBerth>0){
            bookRepo.bookTicket(p,BookRepoImpl.lowBerthPosition.get(0),"l");
            BookRepoImpl.lowBerthPosition.remove(0);
            BookRepoImpl.availLowBerth--;
        }else if(BookRepoImpl.availMidBerth>0){
            bookRepo.bookTicket(p,BookRepoImpl.MidBerthPosition.get(0),"m");
            BookRepoImpl.MidBerthPosition.remove(0);
            BookRepoImpl.availMidBerth--;
        }else if(BookRepoImpl.availHighBerth>0){
            bookRepo.bookTicket(p,BookRepoImpl.HighBerthPosition.get(0),"h");
            BookRepoImpl.HighBerthPosition.remove(0);
            BookRepoImpl.availHighBerth--;
        }else if(BookRepoImpl.availRac>0){
            bookRepo.addToRAC(p,BookRepoImpl.RacPosition.get(0),"rac");
        }else if(BookRepoImpl.availWaitingList>0){
            bookRepo.addToWaiting(p,BookRepoImpl.WaitingListPosition.get(0),"wl");
        }
    }

    @Override
    public void cancelBook(int id) {
        if(!((BookRepoImpl) bookRepo).Passengers.containsKey(id)){
            System.out.println("No ticket with the ID");
        }else{
            bookRepo.cancel(id);
        }
    }

    @Override
    public void printAvailable() {
        System.out.println("Available Lower Berths "  + BookRepoImpl.availLowBerth);
        System.out.println("Available Middle Berths "  + BookRepoImpl.availMidBerth);
        System.out.println("Available Upper Berths "  + BookRepoImpl.availHighBerth);
        System.out.println("Availabel RACs " + BookRepoImpl.availRac);
        System.out.println("Available Waiting List " + BookRepoImpl.availWaitingList);
        System.out.println("--------------------------");
    }
    public void printPassengers(){
        bookRepo.printPassengers();
    }
}
