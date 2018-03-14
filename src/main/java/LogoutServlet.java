import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

@WebServlet("/logoutservlet")
public class LogoutServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
             
            HttpSession session=request.getSession();
            MyListener ml = new MyListener();
            boolean check = ml.check(session.getAttribute("name"));
            
            
             
            if(!check){
                session.invalidate();  
                out.print("You are successfully logged out!");
                out.println("<h3>New Loggin</h3>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            else{
                    out.println("<h3>Login first!!</h3>");
                    request.getRequestDispatcher("login.html").include(request, response);
            }
              
              
            out.close();  
    }  
}  