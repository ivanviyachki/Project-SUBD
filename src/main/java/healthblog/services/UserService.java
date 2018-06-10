package healthblog.services;

import healthblog.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //boolean authenticate(String username, String password);

    User findByEmail(String email) throws SQLException;

    //User findByConfirmationToken(String confirmationToken);

    //User findByForgotPasswordToken(String confirmationToken);

    List<User> getAllUsers() throws SQLException;

    void createUser(User user) throws SQLException;
}
