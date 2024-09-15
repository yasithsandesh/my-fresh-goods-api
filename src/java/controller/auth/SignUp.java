/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dto.request.UserDTO;
import dto.response.ResponseDTO;
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
import util.GenerateKey;
import util.HibernateUtil;
import util.Mail;
import util.Validations;
import util.threads.MailSender;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResponseDTO responseDTO = new ResponseDTO();
        Gson gson = new Gson();

        UserDTO userDTO = gson.fromJson(request.getReader(), UserDTO.class);

        if (userDTO.getFirstName().isEmpty()) {
            responseDTO.setMessage("Please enter your First Name");
      
            responseDTO.setStatus(false);
        } else if (userDTO.getLastName().isEmpty()) {
            responseDTO.setMessage("Please enter your Last Name");
   
            responseDTO.setStatus(false);
        } else if (userDTO.getEmail().isEmpty()) {
            responseDTO.setMessage("Please enter your Email");
      
            responseDTO.setStatus(false);
        } else if (!Validations.isEmailValid(userDTO.getEmail())) {
            responseDTO.setMessage("Please enter your valid Email");
    
            responseDTO.setStatus(false);
        } else if (userDTO.getPassword().isEmpty()) {
            responseDTO.setMessage("Please enter your Password");
 
            responseDTO.setStatus(false);
        } else if (false) {
            responseDTO.setMessage("Password length low");
 
            responseDTO.setStatus(false);
        } else {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("email", userDTO.getEmail()));

            if (!criteria1.list().isEmpty()) {
                responseDTO.setMessage("User with this email already exists");

            } else {

                int code = (int) (Math.random() * 100000000);

                User user = new User();
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                user.setVerification(String.valueOf(code));
                user.setStatus(1);

                MailSender mailSender = new MailSender(user.getEmail(), "My Fresh Goods", user.getVerification());
                mailSender.start();
                int uid = (int) session.save(user);

                session.beginTransaction().commit();
                session.close();

                request.getSession().setAttribute("email", user.getEmail());
                request.getSession().setAttribute("type", "user");
                responseDTO.setMessage("sucess");
                responseDTO.setStatus(true);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(responseDTO));

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Helloo");
    }

}
