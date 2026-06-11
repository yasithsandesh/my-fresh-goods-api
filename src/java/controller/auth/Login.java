/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import com.google.gson.Gson;
import dto.request.UserDTO;
import dto.request.UserLoginDTO;
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
import util.HibernateUtil;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ResponseDTO responseDto = new ResponseDTO();
        
        Gson gson = new Gson();
        
        UserLoginDTO userLoginDTO = gson.fromJson(request.getReader(), UserLoginDTO.class);
        
        if (userLoginDTO.getEmail().isEmpty()) {
            responseDto.setMessage("Please enter your Email");
            
        } else if (userLoginDTO.getPassword().isEmpty()) {
            responseDto.setMessage("Please enter your Password");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("email", userLoginDTO.getEmail()));
            criteria1.add(Restrictions.eq("password", userLoginDTO.getPassword()));
            
            if (!criteria1.list().isEmpty()) {
                
                User user = (User) criteria1.list().get(0);
                
                if (!user.getVerification().equals("verified")) {
                    
                    request.getSession().setAttribute("email", userLoginDTO.getEmail());
                    request.getSession().setAttribute("type", userLoginDTO.getType());
                    responseDto.setMessage("Not verified");
                } else {
                    UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
                    userDTO.setPassword(null);
                    request.getSession().setAttribute("user", userDTO);
                    request.getSession().removeAttribute("email");
                    request.getSession().removeAttribute("type");
                    responseDto.setMessage("login sucess");
                    responseDto.setStatus(true);
                    responseDto.setData(userDTO.getFirstName());
                    int sixMonthsInSeconds = 6 * 30 * 24 * 60 * 60; // Approximately 6 months
                    request.getSession().setMaxInactiveInterval(sixMonthsInSeconds);
                    
                    UserDTO userlog = (UserDTO) request.getSession().getAttribute("user");
                    responseDto.setData(userlog.getFirstName());
                    
                }
                
            } else {
                responseDto.setMessage("Invalid details! please try again");
            }
        }
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseDto));
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseDTO responseDTO = new ResponseDTO<>();
        Gson gson = new Gson();
        
        if(request.getSession().getAttribute("user")!=null){
        
          responseDTO.setStatus(true);
          responseDTO.setMessage("sucesss");
            
        }else{
            
            responseDTO.setMessage("not login");
        
        }
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseDTO));
    }
    
    
}
