/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class Configure extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        boolean door = false;
        boolean window = false;
        
        if(request.getParameter("door") != null){
            door = true;
        } 
        
        if(request.getParameter("window") != null){
            window = true;
        }
        
        Configuration conf = LogicFacade.getConfiguration(length,width,height,door,window);
        request.getSession().setAttribute("configuration", conf);
        return "customerpage";
    }
    
}
