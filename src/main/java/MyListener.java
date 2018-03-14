import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;  

@WebListener
public class MyListener implements ServletContextListener{ 

public void contextInitialized(ServletContextEvent event) {  
try{
    Connection con=null;
    
    Class.forName("com.mysql.jdbc.Driver");
    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classmate", "root", "");
    System.out.println("context started");
    ServletContext ctx=event.getServletContext();
    ctx.setAttribute("conn", con);   

}catch(Exception e){e.printStackTrace();}  
}  
  
public void contextDestroyed(ServletContextEvent arg0) {}

public boolean check(Object name){
    return (name==null);
}
}  