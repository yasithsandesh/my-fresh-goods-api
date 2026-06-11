/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dto.request.ProfileUpdateDTO;
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
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
        Gson gson = new Gson();

        if (request.getSession().getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");

            responseDTO.setData(userDTO);
            responseDTO.setStatus(true);
            responseDTO.setMessage("sucesss");

        } else {

            responseDTO.setMessage("not login");

        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseDTO));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResponseDTO responseDTO = new ResponseDTO();
        Gson gson = new Gson();

        ProfileUpdateDTO profileUpdateDTO = gson.fromJson(request.getReader(), ProfileUpdateDTO.class);

        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");

            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(User.class);

            criteria.add(Restrictions.eq("email", userDTO.getEmail()));

            if (!criteria.list().isEmpty()) {

                User user = (User) criteria.list().get(0);
                user.setFirstName(profileUpdateDTO.getFirstName());
                user.setLastName(profileUpdateDTO.getLastName());

                session.update(user);
                session.beginTransaction().commit();

                userDTO.setFirstName(profileUpdateDTO.getFirstName());
                userDTO.setLastName(profileUpdateDTO.getLastName());

                responseDTO.setData(userDTO);
                responseDTO.setStatus(true);

            }

        } else {

        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseDTO));

    }

}
