/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamlass
 */
public class LogOut extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("user", null);
            String fail = null;
            //Its stupid, i know...
            fail.toString();

        } catch (Exception e) {
            throw new LoginSampleException("Logged Out!");
        }

        return "";
    }

}
