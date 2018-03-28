/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import PresentationLayer.Configuration;
import PresentationLayer.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author adamlass
 */
public class OrderMapper {
    
    public static void placeOrder(Specification spec, int ownerId) throws LoginSampleException{
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO Orders(owner,length,width,height,door,window) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(SQL);
            pre.setInt(1, ownerId);
            pre.setInt(2, spec.getLength());
            pre.setInt(3, spec.getWidth());
            pre.setInt(4, spec.getHeight());
            pre.setBoolean(5, spec.isDoor());
            pre.setBoolean(6, spec.isWindow());
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