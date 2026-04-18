import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addComplaint")
public class AddComplaintServlet extends HttpServlet {

    private Gson json = new Gson();

    protected void do post(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        //get data from frontend
        string name = req.getParameter("name");
        string category = req.getParameter("category");
        string title = req.getParameter("title");
        string description = req.getParameter("description");

        //create the object (like before)
        Complaint complaint = new Complaint(name,category,title,description);

        //add the complaint to the arraylist 
        GetComplaintsServlet.getComplaints().add(complaint);

        //convert to json 
        String response = json.toJson(complaint);// complaint OBJ -> JSON

        //send back the response
        PrintWriter out = res.getWriter();
        out.println(response);
        out.flush();
    }

}