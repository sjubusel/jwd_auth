package by.epamtc.jwd.auth.web.servlet;

import by.epamtc.jwd.auth.model.constant.AppParameter;
import by.epamtc.jwd.auth.web.ajax.AjaxCommandProvider;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 6469747034052425842L;

    private AjaxCommandProvider ajaxProvider = AjaxCommandProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(AppParameter.COMMAND);
        ajaxProvider.execute(command.toUpperCase(), req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String maritalStatusInput = req.getParameter("maritalStatusInput");
//
//        String command = req.getParameter(AppParameter.COMMAND);
//        Map<String, Object> map = new HashMap<>();
//        boolean isValid = false;
//        if (command.equals("temp")) {
//            String countryName = req.getParameter("countryNameInput");
//            if (countryName != null && countryName.trim().length() != 0) {
//                isValid = true;
//                map.put("countryName", countryName);
//            }
//            map.put("isValid", isValid);
//            write(resp, map);
//        } else if (command.equals("tempFetch")) {
//            fetch(req, resp);
//        } else {
//            throw new IllegalArgumentException();
//        }
//    }
//
//    private void write(HttpServletResponse resp, Map<String, Object> map)
//            throws IOException {
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        PrintWriter writer = resp.getWriter();
//        writer.write(new Gson().toJson(map));
//    }
//
//    private void fetch(HttpServletRequest req, HttpServletResponse resp) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        List<String> countries = new ArrayList<>();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/hospital",
//                            "root", "rootroot");
//            statement = connection.prepareStatement("SELECT h.short_country_name FROM hospital.countries h WHERE h.short_country_name LIKE CONCAT('%', ?, '%')");
//            String countryInput = req.getParameter("countryTemp");
//            statement.setString(1, countryInput);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
////                msg.put("isValid", true);
//                countries.add(resultSet.getString(1));
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//        try {
//            resp.getWriter().write(new Gson().toJson(countries));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
