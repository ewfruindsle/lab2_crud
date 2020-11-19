import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/DataBaseServlet")
public class DataBaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/communal_bills",
                    "anastasiia",
                    "qwerty");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM clients");

            while(resultSet.next()){
                pw.println(resultSet.getString("name"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
