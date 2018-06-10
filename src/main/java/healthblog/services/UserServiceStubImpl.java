package healthblog.services;

import healthblog.models.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserServiceStubImpl implements UserService {

    String connectionString = "jdbc:mysql://localhost:3306/health_blog?createDatabaseIfNotExist=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    Connection con = DriverManager.getConnection(connectionString);

    public UserServiceStubImpl() throws SQLException {
    }

    public User findByEmail(String email) throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE Email = ?");

        //TODO CHECK INPUT

        query.setString( 1, email);

        ResultSet result = query.executeQuery();

        //int id = result.getInt("Id");
        String resultEmail = result.getString("Email");
        String fullName = result.getString("FullName");
        String password = result.getString("Password");

        return new User(resultEmail, fullName, password);
    }

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users");

        //TODO CHECK INPUT

        ResultSet result = query.executeQuery();

        List<User> users = new ArrayList<>();
        while (result.next()) {
            String resultEmail = result.getString("Email");
            String fullName = result.getString("FullName");
            String password = result.getString("Password");

            users.add(new User(resultEmail, fullName, password));
        }

        return users;
    }

    public void createUser(User user) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO Users(Email, FullName, Password) VALUES (?, ?, ?)");

        //TODO CHECK INPUT

        query.setString( 1, user.getEmail());
        query.setString( 2, user.getFullName());
        query.setString( 3, user.getPassword());

        int affectedRows = query.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException {
        PreparedStatement query = con.prepareStatement("DELETE FROM Users WHERE Email = ?");

        //TODO CHECK INPUT

        query.setString( 1, user.getEmail());

        int affectedRows = query.executeUpdate();
    }

    public void updateUser(String email, User user) throws SQLException {
        PreparedStatement query = con.prepareStatement("UPDATE Users SET Email = ?, FullName = ?, Password = ? WHERE Email = ?");

        //TODO CHECK INPUT

        query.setString( 1, user.getEmail());
        query.setString( 2, user.getFullName());
        query.setString( 3, user.getPassword());
        query.setString( 4, email);

        int affectedRows = query.executeUpdate();
    }
}
