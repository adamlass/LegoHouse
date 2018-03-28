/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import PresentationLayer.Configuration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author adamlass
 */
public class OrderMapper {
    
    public static void placeOrder(int length, int width, int height, boolean door, 
            boolean window, int ownerId) throws LoginSampleException{
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Orders(owner,length,width,height,door,window) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(SQL);
            pre.setInt(1, ownerId);
            pre.setInt(2, length);
            pre.setInt(3, width);
            pre.setInt(4, height);
            pre.setBoolean(5, door);
            pre.setBoolean(6, window);
            pre.executeUpdate();
            
        } catch (Exception e) {
            throw new LoginSampleException("Ordering Failed!");
        }
        
    }
    
    
    
}



//try {
//            Connection con = Connector.connection();
//            String SQL = "";
//            PreparedStatement pre = con.prepareStatement(SQL);
//            
//            
//        } catch (Exception e) {
//            throw new LoginSampleException("Ordering Failed!");
//        }