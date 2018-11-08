package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingcartservlet", urlPatterns = {"/cart"}, loadOnStartup = 2)
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        String title = "Your shopping cart";
        double totalPrice = 0;

        StringBuilder shoppingCartItems = new StringBuilder();
        for (Item item : ItemStore.getShoppingCartItems()) {
            shoppingCartItems.append(
                    "<tr>" +
                        "<td>" + item.getName() + "</td>" +
                        "<td>" + item.getPrice() + " USD</td>" +
                        "<td><a href=/cart?removeFromCart-id=" + item.getId() + ">Remove</a></td>" +
                    "</tr>"
            );
            totalPrice += item.getPrice();
        }

        String itemRemoveFromCart = request.getParameter("removeFromCart-id");
        if (itemRemoveFromCart != null) {
            ItemStore.removeFromCart(ItemStore.getWebShopItems().get(Integer.parseInt(itemRemoveFromCart)));
        }

        out.println(
                "<html>" +
                    "<head>" +
                        "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>" +
                        "<h1 align=center>" + title + "</h1>" +
                        "<table>" +
                            "<thead>" +
                                "<th>Name</th>" +
                                "<th>Price</th>" +
                            "</thead>" +
                            "<tbody>" +
                                shoppingCartItems.toString() +
                            "</tbody>" +
                        "</table><br>" +
                        "<div><b>Total price: " + totalPrice + " USD</b></div><br>" +
                        "<div><a href=/cart>Refresh your shopping cart</a></div>" +
                        "<div><a href=/>Back to the shop</a></div>" +
                    "</body>" +
                "</html>"
        );

    }

}
