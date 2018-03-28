package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import FunctionLayer.Calculator.LegoHouseCalculator;
import FunctionLayer.Calculator.Position;
import PresentationLayer.Configuration;
import PresentationLayer.Specification;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser(user);
        return user;
    }

    public static Configuration getConfiguration(Specification spec) throws LoginSampleException {
        Configuration res = null;
        try {
            LegoHouseCalculator house = new LegoHouseCalculator(spec.getHeight(),
                    spec.getWidth(),
                    spec.getLength(), spec.isDoor(), spec.isWindow());

            house.build(new Position(0, 0));

            res = house.configuration();
        } catch (Exception | StackOverflowError unused) {
            throw new LoginSampleException("Building Failed!");
        }
        return res;
    }

    public static void placeOrder(Specification spec, User owner) throws LoginSampleException {
        OrderMapper.placeOrder(spec, owner.getId());
    }

}
