package servlet;

import consumer.ServiceConsumer;
import javabean.User;
import service.Bank1Service;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PayServlet",urlPatterns = "/pay")
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String money=request.getParameter("money");
        User user= (User) request.getSession().getAttribute("user");
        ServiceConsumer bank1ServiceConsumer;
        Object obj;
        if ((obj=request.getSession().getServletContext().getAttribute("bank1ServiceConsumer"))!=null){
            bank1ServiceConsumer= (ServiceConsumer) obj;
        }else {
            bank1ServiceConsumer=new ServiceConsumer(Constant.ZK_CONNECTION_STRING,Constant.ZK_SESSION_TIMEOUT,Constant.BANK1_PATH);
            request.getSession().getServletContext().setAttribute("bank1ServiceConsumer",bank1ServiceConsumer);
        }
        Bank1Service bank1Service=bank1ServiceConsumer.lookup();
        if (bank1Service.transfer(user,"qunar",Integer.parseInt(money))){
            request.getRequestDispatcher("/main.html").forward(request,response);
        }else {
            request.getRequestDispatcher("/index.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
