import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addComplaint")