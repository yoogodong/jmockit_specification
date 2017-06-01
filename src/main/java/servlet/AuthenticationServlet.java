package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * to show : partial mock
 * Created by Yonggao.Dong on 15/9/8.
 */
public class AuthenticationServlet extends HttpServlet{


    @Inject
    private AccountDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req,resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (dao.isValid(name,password)){
            req.getRequestDispatcher("/home").forward(req,resp);
        }else{
            req.setAttribute("errorMessage","your name or password is valid");
            req.getRequestDispatcher("/login").forward(req,resp);
        }
    }
}
