import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {  

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
         
          
        String name=request.getParameter("name");  
        String password=request.getParameter("password");
        int n = 0;
        
        HttpSession session=request.getSession();

        PreparedStatement ps = null;
        ResultSet rs = null;
        ServletContext ctx=request.getServletContext();
        Connection con = (Connection)ctx.getAttribute("conn");
        
        try{
        ps=con.prepareStatement("select uname from userdata");
        rs=ps.executeQuery();
        MyListener ml = new MyListener();
        boolean check = ml.check(session.getAttribute("name"));
        
        
        if(check){
        if(name == ""){
            out.println("<h3>Enter a username to loggin!!</h3>");
            request.getRequestDispatcher("login.html").include(request, response);
        }  
        else{
            try {
				while(rs.next()){
            if(name.equals(rs.getString(1))){
                if(password.equals(name)){
                    n++;
                    out.print("Welcome, "+name);
                    out.println("<br></br>");
                    request.getRequestDispatcher("link.html").include(request, response);
                    session=request.getSession();
                    session.setAttribute("name",name);
                    break;
                }
                else{
                    n++;
                    out.print("<h3>Sorry, Password Missmatch!</h3>");
                    request.getRequestDispatcher("login.html").include(request, response);
                    break;
                }
            }
        }
        if(n==0){
        out.println("Name is not found in DB!!");
        request.getRequestDispatcher("login.html").include(request, response);
        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
        else{
         out.println("<h3>Logout the current account!!</h3>");
         out.println("<a href=\"\\logoutservlet\"><button>Logout</button></a>");
     }
    
        out.close();  
    }catch (Exception e){
        e.printStackTrace();
    }
}
       
}  