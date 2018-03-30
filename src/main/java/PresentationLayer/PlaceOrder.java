/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class PlaceOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        HttpSession session = request.getSession();
        Specification spec = (Specification) session
                .getAttribute("specification");

        User owner = (User) request.getSession().getAttribute("user");
        LogicFacade.placeOrder(spec, owner);
        session.setAttribute("specification", null);
        session.setAttribute("configuration", null);
        session.setAttribute("orderok", "notnull"); //the only way that worked...

        return "customerpage";
    }

}
