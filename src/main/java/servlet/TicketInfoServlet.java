package servlet;

import com.google.gson.Gson;
import consumer.ServiceConsumer;
import javabean.Ticket;
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

@WebServlet(name = "TicketInfoServlet",urlPatterns = "/getTicketInfo")
public class TicketInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> ticketList=new ArrayList<>();
        String startAddress=request.getParameter("startAddress");
        String endAddress=request.getParameter("endAddress");
        String date=request.getParameter("date");
        ServiceConsumer agent1ServiceConsumer;
        Object obj;
        if ((obj=request.getSession().getServletContext().getAttribute("agent1ServiceConsumer"))!=null){
            agent1ServiceConsumer= (ServiceConsumer) obj;
        }else {
            agent1ServiceConsumer=new ServiceConsumer(Constant.ZK_CONNECTION_STRING,Constant.ZK_SESSION_TIMEOUT,Constant.AGENT1_PATH);
            request.getSession().getServletContext().setAttribute("agent1ServiceConsumer",agent1ServiceConsumer);
        }
        Agent1Service agent1Service=agent1ServiceConsumer.lookup();
        ticketList=agent1Service.queryTickets(startAddress,endAddress,date);
        request.getSession().setAttribute("tickets",ticketList);
        Gson gson=new Gson();
        String json=gson.toJson(ticketList);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
