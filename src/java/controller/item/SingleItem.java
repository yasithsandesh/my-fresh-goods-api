/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.item;

import com.google.gson.Gson;
import dto.response.ResponseDTO;
import dto.response.SingleItemResponseDTO;
import entity.Category;
import entity.Item;
import entity.ItemStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "SingleItem", urlPatterns = {"/SingleItem"})
public class SingleItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            Item item = (Item) session.get(Item.class, Integer.valueOf(pid));
            item.getGarden().setOwner(null);
            ItemStatus itemStatus = (ItemStatus) session.get(ItemStatus.class, 3);

            Category category = item.getCategory();

            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.eq("category", category));
            criteria.add(Restrictions.eq("itemStatus", itemStatus));
            criteria.add(Restrictions.ne("id", item.getId()));

            criteria.setMaxResults(5);

            List<Item> items = criteria.list();

            for (Item itemis : items) {

                itemis.getGarden().setOwner(null);

            }
            Gson gson = new Gson();
            SingleItemResponseDTO singleItemResponseDTO = new SingleItemResponseDTO();
            singleItemResponseDTO.setSimilarItemsList(items);
            singleItemResponseDTO.setItem(item);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(singleItemResponseDTO));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
