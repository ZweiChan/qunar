package service;


import javabean.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank1Service extends Remote {
    //public void register(String id, String name, String password)throws RemoteException;//注册
    //public boolean login(String id, String password)throws RemoteException;//登录
    //public void saveMoney(String id, int money)throws RemoteException;//存款
    //public void query(String id)throws RemoteException;//查询余额
    //public void withdraw(String id, int money)throws RemoteException;//取款
    public boolean transfer(User user, String targetId, int money)throws RemoteException;//转账
}
