package s08;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s08/checker")
public class Checker extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user"); //prende parametro di user della richiesta 
        Set<Character> set = new TreeSet<>(); //set perchè non così non ci saranno duplicati e perchè è meglio lavorare su interfaccie
        if (user != null) {
            for (char c : user.toCharArray()) { //loop su tutti i caratteri di user + metodo che ritorna user, un array di caratteri 
                set.add(Character.toLowerCase(c));// per ogni char, inserisci nel set, tutte minuscole
            }
        }
        request.setAttribute("set", set); //chiave- stringa, valore-object...salva set come attributo, all'interno di request.
        									// attributo: è una sorta di parametro utile per comunicare dati al JSP. assegnando set a request JSP lo può leggere.
        RequestDispatcher rd = request.getRequestDispatcher("/s08/checker.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
