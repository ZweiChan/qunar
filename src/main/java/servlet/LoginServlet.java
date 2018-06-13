package servlet;

import dao.QunarDao;
import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String pwd=request.getParameter("pwd");
        QunarDao dao=new QunarDao();
        User user=dao.readUser(id);
        if (user!=null && user.getPwd().equals(pwd)){
            request.getSession().setAttribute("user",user);
            response.sendRedirect("/main.html");
        }else {
            request.getRequestDispatcher("/index.html").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
