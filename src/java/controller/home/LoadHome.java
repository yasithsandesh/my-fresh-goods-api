/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.home;

import com.google.gson.Gson;
import dto.response.LoadHomeDTO;
import dto.response.TopCategory;
import entity.Category;
import entity.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author yasithsandesh
 */
@WebServlet(name = "LoadHome", urlPatterns = {"/LoadHome"})
public class LoadHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria1 = session.createCriteria(Category.class);
            criteria1.setMaxResults(10);
            List<Category> categoryList = criteria1.list();

            Criteria criteria = session.createCriteria(Item.class);
            List<Item> itemList = criteria.list();

            LinkedHashSet<Category> itemCategorys = new LinkedHashSet<>();

            List<TopCategory> topCategorys = new ArrayList<>();

            for (Item item : itemList) {
                itemCategorys.add(item.getCategory());
            }

            for (Category category : itemCategorys) {

                TopCategory topCategory = new TopCategory();
                topCategory.setCategory(category);
                List<Item> items = new ArrayList<>();
                for (Item item : itemList) {

                    if (category.getId() == item.getCategory().getId()) {
                        items.add(item);
                    }

                }

                topCategory.setItems(items);
                topCategorys.add(topCategory);

            }

            LoadHomeDTO loadHomeDTO = new LoadHomeDTO();
            loadHomeDTO.setCategorys(categoryList);
            loadHomeDTO.setTopCategorys(topCategorys);

            response.setContentType("application/json");
            response.getWriter().print(gson.toJson(loadHomeDTO));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
