package by.epamtc.jwd.auth.web.servlet;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = -8875701472070281217L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maritalStatusInput = req.getParameter("maritalStatusInput");

        String command = req.getParameter(AppParameter.COMMAND);
        Map<String, Object> map = new HashMap<>();
        boolean isValid = false;
        if (command.equals("temp")) {
            String countryName = req.getParameter("countryNameInput");
            if (countryName != null && countryName.trim().length() != 0) {
                isValid = true;
                map.put("countryName", countryName);
            }
            map.put("isValid", isValid);
            write(resp, map);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void write(HttpServletResponse resp, Map<String, Object> map)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(new Gson().toJson(map));
    }
}
