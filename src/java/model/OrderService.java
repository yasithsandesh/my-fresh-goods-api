/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.response.ResponseDTO;
import entity.Address;
import entity.Cart;
import entity.Item;
import entity.OrderItem;
import entity.OrderStatus;
import entity.Orders;
import entity.User;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Payhere;

/**
 *
 * @author yasithsandesh
 */
public class OrderService {

    public void saveOrder(Session session, Transaction transaction, User user, Address address, JsonObject responseJsonObject) {

        try {

            Orders orders = new Orders();
            orders.setAddress(address);
            orders.setDate(new Date());
            orders.setUser(user);

            int orderId = (int) session.save(orders);

            //get user cart item list
            Criteria getCartItemList = session.createCriteria(Cart.class);
            List<Cart> cartItemList = getCartItemList.list();

            //get Order status
            OrderStatus orderStatus = (OrderStatus) session.get(OrderStatus.class, 2);

            double total = 0;

            //get cartItem list read one by one
            for (Cart cart : cartItemList) {

                total += cart.getQty() * cart.getItem().getPrice();

                // get item
                Item item = cart.getItem();

                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setOrderStatus(orderStatus);
                orderItem.setOrders(orders);
                orderItem.setQty(cart.getQty());

                //update item qty
                item.setQty(item.getQty() - cart.getQty());
                session.update(item);

                // save orderItem
                session.save(orderItem);

                // delete cart item
                session.delete(cart);

            }

            transaction.commit();

            //set payment data
            String merchant_id = "1221233";
            String formatedAmount = new DecimalFormat("0.00").format(total);
            String currency = "LKR";
            String merchantSecret = Payhere.generateMD5("MzM2NjE0NTI5MzM3ODYxMTg3MjA1NjQ4NjMwMjI3MjYzMTY4NDY=");

            JsonObject payhere = new JsonObject();
            payhere.addProperty("merchant_id", merchant_id);

            payhere.addProperty("return_url", "");
            payhere.addProperty("cancel_url", "");
            payhere.addProperty("notify_url", "VerifyPayments");

            payhere.addProperty("first_name", user.getFirstName());
            payhere.addProperty("last_name", user.getLastName());
            payhere.addProperty("email", user.getEmail());
            payhere.addProperty("phone", "0773720462");
            payhere.addProperty("address", "no23, palliayawattha, pannala");
            payhere.addProperty("city", "Kurunegala");
            payhere.addProperty("country", "Sri-Lanka");
            payhere.addProperty("order_id", String.valueOf(orderId));
            payhere.addProperty("items", "");
            payhere.addProperty("currency", currency);
            payhere.addProperty("amount", formatedAmount);
            payhere.addProperty("sandbox", true);

            //generate md5
            String md5Hash = Payhere.generateMD5(merchant_id + orderId + formatedAmount + currency + merchantSecret);
            payhere.addProperty("hash", md5Hash);

            responseJsonObject.addProperty("success", true);
            responseJsonObject.addProperty("message", "Checkout Completed");

            Gson gson = new Gson();
            responseJsonObject.add("payhereJson", gson.toJsonTree(payhere));

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }

}
