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

/**
 *
 * @author adamlass
 */
public class PlaceOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        boolean door = false;
        boolean window = false;
        
        if(request.getParameter("door") != null){
            door = true;
        } 
        
        if(request.getParameter("window") != null){
            window = true;
        }
        
        User owner = (User) request.getSession().getAttribute("user");
        
        LogicFacade.placeOrder(length, width, height, door, window, owner);
        return "cutomerpage";
    }
    
}
