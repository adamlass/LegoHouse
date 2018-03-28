/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import PresentationLayer.Configuration;
import PresentationLayer.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class OrderMapper {

    public static void placeOrder(Specification spec, int ownerId) throws LoginSampleException {
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

    public static List<Order> orders(User owner) throws LoginSampleException {
        List<Order> res = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            StringBuilder SQL = new StringBuilder("SELECT * FROM Orders");

            if (owner != null) {
                SQL.append(" WHERE owner = ?");
            }

            PreparedStatement pre = con.prepareStatement(SQL.toString());

            if (owner != null) {
                pre.setInt(1, owner.getId());
            }

            ResultSet rs;
            rs = pre.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int length = rs.getInt("length");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                boolean door = rs.getBoolean("door");
                boolean window = rs.getBoolean("window");
                boolean sent = rs.getBoolean("sent");

                int dbowner = rs.getInt("owner");
                User orderOwner = UserMapper.find(dbowner);
                
                res.add(new Order(id, length, width, height, orderOwner, window, 
                        door, sent));
            }

        } catch (Exception e) {
            throw new LoginSampleException("Failed looking at orders!");
        }
        return res;
    }

    public static void mark(int order) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE Orders SET sent=1 WHERE id=?";
            PreparedStatement pre = con.prepareStatement(SQL);
            pre.setInt(1, order);
            pre.execute();
            
        } catch (Exception e) {
            throw new LoginSampleException("Sending Order Failed!");
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
