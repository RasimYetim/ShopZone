package servlet;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gelen mesajı oku
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        // JSON'dan mesajı al
        Gson gson = new Gson();
        Map<String, String> requestMap = gson.fromJson(requestBody, HashMap.class);
        String userMessage = requestMap.get("message");

        // Basit bir yanıt sistemi
        String reply = "Bu mesajı anladım: " + userMessage;

        // Yanıtı JSON olarak gönder
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("reply", reply);
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseMap));
    }
}
