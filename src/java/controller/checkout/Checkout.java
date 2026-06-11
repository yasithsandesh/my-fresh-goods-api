/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.checkout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.request.AddressRequestDTO;
import dto.request.UserDTO;
import dto.response.ResponseDTO;
import entity.Address;
import entity.City;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {

    OrderService OrderService;

    @Override
    public void init() throws ServletException {
        this.OrderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        AddressRequestDTO addressRequestDTO = gson.fromJson(request.getReader(), AddressRequestDTO.class);
        JsonObject responseJsonObject = new JsonObject();
        responseJsonObject.addProperty("success", false);

        try {
            HttpSession httSession = request.getSession();
            
          

            if (httSession.getAttribute("user") != null) {

                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();

                UserDTO userDTO = (UserDTO) httSession.getAttribute("user");
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("email", userDTO.getEmail()));

                User user = (User) criteria.uniqueResult();

                if (addressRequestDTO.isIsCurrentAddress()) {
                    //current address
                    Criteria criteria1 = session.createCriteria(Address.class);
                    criteria1.add(Restrictions.eq("user", user));
                    criteria1.addOrder(Order.desc("id"));
                    criteria1.setMaxResults(1);

                    if (criteria1.list().isEmpty()) {
                        //current address not found. Please create a new address.
                        responseJsonObject.addProperty("message", "Current address not found. Please create a new address");
                    } else {

                        Address address = (Address) criteria1.list().get(0);

                        this.OrderService.saveOrder(session, transaction, user, address, responseJsonObject);

                    }

                } else {
                    //save new address

                    if (addressRequestDTO.getFirstName().isEmpty()) {
                        responseJsonObject.addProperty("message", "Please fill first name");
                    } else if (addressRequestDTO.getLastName().isEmpty()) {

                        responseJsonObject.addProperty("message", "Please fill last name");
                    } else {

                        City city = (City) session.get(City.class, addressRequestDTO.getCityId());

                        if (city == null) {
                            responseJsonObject.addProperty("message", "Invalid City selected");

                        } else {

                            if (addressRequestDTO.getLine1().isEmpty()) {
                                responseJsonObject.addProperty("message", "Please fill address line 1");
                            } else if (addressRequestDTO.getLine2().isEmpty()) {
                                responseJsonObject.addProperty("message", "Please fill address line 1");

                            } else if (addressRequestDTO.getPostalCode().isEmpty()) {
                                responseJsonObject.addProperty("message", "Please fill podtsl code");

                            } else if (addressRequestDTO.getMobile().isEmpty()) {
                                responseJsonObject.addProperty("message", "Please fill mobile");
                            } else {

                                Address address = new Address();
                                address.setCity(city);
                                address.setFirstName(addressRequestDTO.getFirstName());
                                address.setLastName(addressRequestDTO.getLastName());
                                address.setLine1(addressRequestDTO.getLine1());
                                address.setLine2(addressRequestDTO.getLine2());
                                address.setMobile(addressRequestDTO.getMobile());
                                address.setPostalCode(addressRequestDTO.getPostalCode());
                                address.setUser(user);

                                session.save(address);

                                this.OrderService.saveOrder(session, transaction, user, address, responseJsonObject);

                            }

                        }

                    }
                }

            } else {

                responseJsonObject.addProperty("message", "user not signed in");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJsonObject));
    }

}
