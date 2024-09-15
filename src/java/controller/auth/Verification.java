/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.request.UserDTO;
import dto.response.ResponseDTO;
import entity.Owner;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "Verification", urlPatterns = {"/Verification"})
public class Verification extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        Gson gson = new Gson();
        JsonObject dto = gson.fromJson(request.getReader(), JsonObject.class);
        String verification = dto.get("verification").getAsString();

        if (request.getSession().getAttribute("email") != null) {

            if (request.getSession().getAttribute("type") != null) {

                String email = request.getSession().getAttribute("email").toString();

                Session session = HibernateUtil.getSessionFactory().openSession();

                if (request.getSession().getAttribute("type").toString().equals("user")) {

                    Criteria criteria1 = session.createCriteria(User.class);
                    criteria1.add(Restrictions.eq("email", email));
                    criteria1.add(Restrictions.eq("verification", verification));

                    if (!criteria1.list().isEmpty()) {

                        User user = (User) criteria1.list().get(0);
                        user.setVerification("verified");

                        session.update(user);
                        session.beginTransaction().commit();

                        request.getSession().removeAttribute("email");
                        responseDTO.setMessage("Verification Success");
                        responseDTO.setStatus(true);
                        response.setContentType("application/json");
                        response.getWriter().write(gson.toJson(responseDTO));

                    } else {

                        responseDTO.setMessage("Invalid Verification code");
                    }

                } else {
                    Criteria criteria = session.createCriteria(Owner.class);
                    criteria.add(Restrictions.eq("email", email));
                    criteria.add(Restrictions.eq("verification", verification));

                    if (!criteria.list().isEmpty()) {

                        Owner owner = (Owner) criteria.list().get(0);
                        owner.setVerification("verified");

                        session.update(owner);
                        session.beginTransaction().commit();

                        request.getSession().removeAttribute("email");
                        request.getSession().removeAttribute("type");
                        
                        responseDTO.setStatus(true);
                        responseDTO.setMessage("Verification Success");
                        responseDTO.setCode(200);
        
                    } else {

                    }
                }

            } else {
                responseDTO.setMessage("Verification unavailable! Please Login");
            }

        } else {
            responseDTO.setMessage("Verification unavailable! Please Login");
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseDTO));
    }

}
