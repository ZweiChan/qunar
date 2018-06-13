package service;

import javabean.Order;
import javabean.Ticket;
import javabean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Agent1Service extends Remote {
    public List<Ticket> queryTickets(String startAddress, String endAddress, String date)throws RemoteException;
    public Order booking(User user, Ticket ticket)throws RemoteException;
    public List<Order> queryOrders(User user)throws RemoteException;
}
