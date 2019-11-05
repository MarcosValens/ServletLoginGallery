import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="login",urlPatterns="/login")
public class loginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        String firstName = req.getParameter("firstname");
        String password = req.getParameter("password");
        if (firstName.equals("esliceu") && password.equals("esliceu")){
            resp.sendRedirect("upload.html");
        } else resp.sendRedirect("loginError.html");
    }
}