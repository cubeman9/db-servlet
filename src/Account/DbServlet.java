package Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dmitry on 11.10.2016.
 */
public class DbServlet extends HttpServlet {
    private AtomicInteger requestCount = new AtomicInteger();
    private AtomicLong startTime = new AtomicLong(0);

    public void init() {
        requestCount = new AtomicInteger(0);
        startTime.addAndGet(System.currentTimeMillis() / 1000L);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestCount.incrementAndGet();
        long timeFromInit = (System.currentTimeMillis() / 1000L) - startTime.longValue();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String actionString = request.getParameter("action");

        if (actionString.equals("setRequestCountToZero")) {
            requestCount = new AtomicInteger(0);
            String jsonStr = "{\"action\": " + "\"" + actionString + "\"" +
                    ",\"requestCount\": " + requestCount.intValue() +
                    ",\"timeFromInit\": " + timeFromInit + "}";
            out.println(jsonStr);
        }

        if (actionString.equals("sendRequestCountInfo")) {
            String jsonStr = "{\"action\": " + "\"" + actionString + "\"" +
                    ",\"requestCount\": " + requestCount.intValue() +
                    ",\"timeFromInit\": " + timeFromInit + "}";
            out.println(jsonStr);
        }

        if (actionString.equals("get") || actionString.equals("add")) {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);

            if (actionString.equals("get")) {
                String jsonStr = "{\"id\": " + id +
                        ",\"balance\": " + AccountService.getAmount(id) +
                        ",\"action\": " + "\"" + actionString + "\"" +
                        ",\"requestCount\": " + requestCount.intValue() +
                        ",\"timeFromInit\": " + timeFromInit + "}";
                out.println(jsonStr);
            }
            else if (actionString.equals("add")) {
                String balanceString = request.getParameter("balance");
                int balance = Integer.parseInt(balanceString);

                AccountService.addAmount(id, balance);
                        String jsonStr = "{\"id\": " + id +
                                ",\"balance\": " + AccountService.getAmount(id) +
                                ",\"action\": " + "\"" + actionString + "\"" +
                                ",\"requestCount\": " + requestCount.intValue() +
                                ",\"timeFromInit\": " + timeFromInit + "}";
                        out.println(jsonStr);


            }
        }
    }
}
