package s07;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s07/timerPlain") //l'annotazione serve a dire che è associato a questo indirizzo. è una webservlet che risponde alla (risorsa). quando l'utente fa richiesta alla webapp, tom cat prima, controlla se qualcuno la gestisce.
public class TimerPlain extends HttpServlet { 
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //do get perchè il  metodo utilizzato era GET
            throws ServletException, IOException {									// se fosse stato metodo POST, avrei fatto doPost
        response.setContentType("text/plain"); //generare una response: mando un file di testo, codificato a utf8
        response.setCharacterEncoding("utf-8");
        try (PrintWriter writer = response.getWriter()) {//printwriter come scanner. esso funziona come syso println
            writer.println(LocalTime.now());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); //delega comunque l'esecuzione a doGet
    }
}
