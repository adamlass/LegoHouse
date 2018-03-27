package FunctionLayer;

import DBAccess.UserMapper;
import PresentationLayer.Configuration;

/**
 * The purpose of LogicFacade is to...
 * @author kasper
 */
public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }

    public static Configuration getConfiguration(String length, String width, String height, boolean door, boolean window) {
        return new Configuration(4, 0, 4, door, window);
    }

}
