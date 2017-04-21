package com.edufound.web.action.product;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
 
public class SaleTicket {
    public static void main(String[] args) {
        BlockingQueue<Integer> tickets = new LinkedBlockingQueue<Integer>();
        for (int i = 1; i <= 100; i++) {
            tickets.add(i);
        }
        new Window("window1",tickets).start();
        new Window("window2",tickets).start();
        new Window("window3",tickets).start();
        new Window("window4",tickets).start();
        new Window("window5",tickets).start();   
    }
}
 
class Window extends Thread {
    public String name;
    private BlockingQueue<Integer> tickets;
 
    public Window(String name, BlockingQueue<Integer> tickets) {
        this.name = name;
        this.tickets = tickets;
    }
    public void run() {
        while(!tickets.isEmpty()){
            try {
                int ticket = tickets.take();
                System.out.println(name+" take ticket: "+ticket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+" no more tickets ! ");
    }
}