import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/DeleteComplaintServlet")
public class DeleteComplaintServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String title = req.getParameter("title");

        try {
            Connection conn = DBconnection.getConnection();

            String sql = "DELETE FROM complaints WHERE title=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);

            int rows = ps.executeUpdate();

            res.setContentType("text/plain");

            if (rows > 0)
                res.getWriter().print("success");
            else
                res.getWriter().print("failed");

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}