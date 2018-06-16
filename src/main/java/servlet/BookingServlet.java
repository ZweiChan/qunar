package servlet;

import consumer.ServiceConsumer;
import javabean.Order;
import javabean.Ticket;
import javabean.User;
import service.Agent1Service;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookingServlet",urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketId=request.getParameter("ticketId");
        Ticket ticket=new Ticket();
        List<Ticket> tickets= (ArrayList<Ticket>) request.getSession().getAttribute("tickets");
        for (Ticket t:tickets
             ) {
            if (t.getId().equals(ticketId)){
                ticket=t;break;
            }
        }
        User user= (User) request.getSession().getAttribute("user");
        ServiceConsumer agent1ServiceConsumer;
        Object obj;
        if ((obj=request.getSession().getServletContext().getAttribute("agent1ServiceConsumer"))!=null){
            agent1ServiceConsumer= (ServiceConsumer) obj;
        }else {
            agent1ServiceConsumer=new ServiceConsumer(Constant.ZK_CONNECTION_STRING,Constant.ZK_SESSION_TIMEOUT,Constant.AGENT1_PATH);
            request.getSession().getServletContext().setAttribute("agent1ServiceConsumer",agent1ServiceConsumer);
        }
        Agent1Service agent1Service=agent1ServiceConsumer.lookup();
        Order order=agent1Service.booking(user,ticket);
        if (order!=null){
            request.getSession().setAttribute("order",order);
            response.getWriter().write("1");
        }else {
            response.getWriter().write("0");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
