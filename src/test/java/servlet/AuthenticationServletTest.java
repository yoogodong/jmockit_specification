package servlet;

import mockit.*;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by twer on 2017/6/1.
 */
public class AuthenticationServletTest {

    @Tested
    AuthenticationServlet authenticationServlet;

    @Injectable
    AccountDao accountDao;


    @Mocked
    HttpServletRequest request;
    @Mocked
    HttpServletResponse response;

    @Test
    public void should_forward_to_home_if_login_valid() throws Exception {

        new Expectations() {{
            accountDao.isValid(anyString, anyString);
            result = true;
        }};

        authenticationServlet.doGet(request, response);

        new Verifications() {{
            request.getRequestDispatcher("/home").forward(request, response);
        }};
    }

    @Test
    public void should_forward_to_logonPage_when_login_invalid() throws Exception {

        new Expectations() {{
            accountDao.isValid(anyString, anyString);
            result = false;
        }};

        authenticationServlet.doGet(request, response);

        new Verifications() {{
            request.setAttribute("errorMessage", "your name or password is valid");
            request.getRequestDispatcher("/login").forward(request, response);
        }};
    }


}