package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "webshopservlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {

    public void init() {
        ItemStore.appendWebShopItems(new Item("Jonathan Franzen: Corrections", 20));
        ItemStore.appendWebShopItems(new Item("Nathan Hill: The Nix", 25));
        ItemStore.appendWebShopItems(new Item("David Foster Wallace: Infinite Jest", 30));
        ItemStore.appendWebShopItems(new Item("Kindle Paperwhite", 300));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        String title = "Codecool BookShop";

        StringBuilder webShopItems = new StringBuilder();
        for (Item item : ItemStore.getWebShopItems()) {
            webShopItems.append(
                    "<tr>" +
                        "<td>" + item.getName() + "</td>" +
                        "<td>" + item.getPrice() + " USD</td>" +
                        "<td><a href=/webshop?addToCart-id=" + item.getId() + ">Add</a></td>" +
                    "</tr>"
            );
        }

        String itemAddToCart = request.getParameter("addToCart-id");
        if (itemAddToCart != null) {
            ItemStore.addToCart(ItemStore.getWebShopItems().get(Integer.parseInt(itemAddToCart)));
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
                                webShopItems.toString() +
                            "</tbody>" +
                        "</table><br>" +
                        "<div><a href=/cart>Proceed to checkout</a></div>" +
                    "</body>" +
                "</html>"
        );

    }

}
