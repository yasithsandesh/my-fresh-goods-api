/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dto.response.ResponseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "LogOut", urlPatterns = {"/LogOut"})
public class LogOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        Gson gson = new Gson();
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate(); // Deletes the session
            responseDTO.setStatus(true);
        }

        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responseDTO));
    }

}
