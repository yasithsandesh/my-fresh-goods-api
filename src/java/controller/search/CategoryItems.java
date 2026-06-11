/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.search;

import com.google.gson.Gson;
import dto.response.ProductSearchResponse;
import entity.Category;
import entity.Item;
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
@WebServlet(name = "CategoryItems", urlPatterns = {"/CategoryItems"})
public class CategoryItems extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String categoryId = request.getParameter("id");
        ProductSearchResponse productSearchResponse = new ProductSearchResponse();
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();

            //get all items by db
            Criteria criteria1 = session.createCriteria(Item.class);

            // get category
            Category category = (Category) session.get(Category.class, Integer.parseInt(categoryId));

            criteria1.add(Restrictions.eq("category", category));

            criteria1.setFirstResult(Integer.parseInt(request.getParameter("page")));
            criteria1.setMaxResults(10);

            List<Item> items = criteria1.list();

            for (Item item : items) {

                item.setGarden(null);

            }

            productSearchResponse.setItems(items);
            productSearchResponse.setAllItemCount(items.size());

        } catch (Exception e) {
            e.printStackTrace();

        }

        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(productSearchResponse));
    }

}
