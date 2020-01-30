package s09;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s09/greeter")
public class Greeter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); //quando tomcat riceve una request, controlla nella session se c'è qualche cookie associato all'utente
        LocalTime start = (LocalTime) session.getAttribute("start");//se fosse la prima sessione non ci sarà alcun attributo, quindi ritornerà un null. al contrario avremo un local time valido.

        Duration duration; //da quanto ttempo l'utente è collegato
        if (start == null) { //quindi se fosse la prima volta che accede...
            duration = Duration.ZERO;
            session.setAttribute("start", LocalTime.now());// metterà l'ora attuale
        } else {
            duration = Duration.between(start, LocalTime.now());//quanto tempo è passato dall'ultima volta che si è collegato e ora 
        }

        if (request.getParameter("done") == null) { //done è la chiave per dire che il lavoro è terminato
            request.setAttribute("duration", duration); //metti attributo di quanto tempo è passato dal log
            RequestDispatcher rd = request.getRequestDispatcher("/s09/greeter.jsp");
            rd.forward(request, response);
        } else {
            session.invalidate(); //disconnessione session

            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            try (PrintWriter writer = response.getWriter()) {
                writer.println("<!DOCTYPE html><html>");
                writer.println("<head><meta charset=\"utf-8\">");
                writer.println("<title>So long</title></head>");
                writer.println("<body><h1>Goodbye</h1>");
                writer.println("<p>You worked on this stuff for " + duration.getSeconds() + " seconds</p>");
                writer.println("</body></html>");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
