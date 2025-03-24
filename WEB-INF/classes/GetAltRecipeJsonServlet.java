import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.cbigames.satisfactorsheets.*;

public class GetAltRecipeJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        InputStream jsonFileIn =  Generator.class.getClassLoader().getResourceAsStream("alts.json");
        byte[] jsonBytes = jsonFileIn.readAllBytes();
        response.setContentLength(jsonBytes.length);
        OutputStream out = response.getOutputStream();
        out.write(jsonBytes);
        jsonFileIn.close();
    }
}
