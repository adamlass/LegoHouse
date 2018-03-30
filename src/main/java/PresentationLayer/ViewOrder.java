/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class ViewOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            HttpSession session = request.getSession();
            List<Order> orders = (List<Order>) session.getAttribute("orders");
            int id = Integer.parseInt(request.getParameter("id"));

            Order subject = null;
            for (Order order : orders) {
                if (order.getId() == id) {
                    subject = order;
                }
            }
            Configuration conf = LogicFacade.getConfiguration(new Specification(subject.getLength(),
                    subject.getWidth(), subject.getHeight(), subject.isDoor(),
                    subject.isWindow()));

            session.setAttribute("configurationview", conf);

        } catch (Exception ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return "orders";
    }

}
