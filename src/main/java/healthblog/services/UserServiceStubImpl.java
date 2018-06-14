package healthblog.services;

import healthblog.models.Article;
import healthblog.models.Role;
import healthblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service("userService")
public class UserServiceStubImpl implements UserService {

    @Autowired
    private JdbcConnection jdbcConnection;

    @Autowired
    private RoleServiceStubImpl roleService;

    public UserServiceStubImpl() throws SQLException {
    }

    @Override
    public User findById(Integer id) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE Id = ?");

        //TODO CHECK INPUT

        query.setInt( 1, id);

        ResultSet result = query.executeQuery();

        String resultEmail = result.getString("Email");
        String resultFullName = result.getString("FullName");
        String resultPassword = result.getString("Password");

        Set<Integer> resultRoles = getRoles(id);
        Set<Integer> resultArticles = getArticles(id);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return new User(id, resultEmail, resultFullName, resultPassword, resultRoles, resultArticles);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users WHERE Email = ?");

        //TODO CHECK INPUT

        query.setString( 1, email);

        ResultSet result = query.executeQuery();

        Integer resultId = result.getInt("Id");
        String resultFullName = result.getString("FullName");
        String resultPassword = result.getString("Password");

        Set<Integer> resultRoles = getRoles(resultId);
        Set<Integer> resultArticles = getArticles(resultId);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return new User(resultId, email, resultFullName, resultPassword, resultRoles, resultArticles);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Users");

        //TODO CHECK INPUT

        ResultSet result = query.executeQuery();

        List<User> users = new ArrayList<>();
        while (result.next()) {
            Integer resultId = result.getInt("Id");
            String resultEmail = result.getString("Email");
            String fullName = result.getString("FullName");
            String password = result.getString("Password");

            Set<Integer> resultRoles = getRoles(resultId);
            Set<Integer> resultArticles = getArticles(resultId);

            users.add(new User(resultId, resultEmail, fullName, password, resultRoles, resultArticles));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return users;
    }

    @Override
    public void createUser(User user) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("INSERT INTO Users(Email, FullName, Password) VALUES (?, ?, ?)");

        //TODO CHECK INPUT

        query.setString( 1, user.getEmail());
        query.setString( 2, user.getFullName());
        query.setString( 3, user.getPassword());

        int affectedRows = query.executeUpdate();

//        Integer id = findByEmail(user.getEmail()).getId();
//
//        query = con.prepareStatement("INSERT INTO Users_Roles(UserId, RoleId) VALUES (?, ?)");
//
//        query.setInt( 1, id);
//        query.setInt( 2, );

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("DELETE FROM Users WHERE Id = ?");

        //TODO CHECK INPUT

        query.setInt( 1, user.getId());

        int affectedRows = query.executeUpdate();

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public void updateUser(String email, User user) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();



        PreparedStatement query = con.prepareStatement("UPDATE Users SET Email = ?, FullName = ?, Password = ? WHERE Email = ?");

        //TODO CHECK INPUT

        query.setString( 1, user.getEmail());
        query.setString( 2, user.getFullName());
        query.setString( 3, user.getPassword());
        query.setString( 4, email);

        int affectedRows = query.executeUpdate();

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public Set<Integer> getRoles(Integer userId) throws SQLException {

        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT RoleId FROM Users_Roles WHERE UserId = ?");

        //TODO CHECK INPUT

        query.setInt(1, userId);

        ResultSet result = query.executeQuery();

        Set<Integer> roles = new HashSet<>();
        while (result.next()) {
            roles.add(result.getInt("RoleId"));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return roles;
    }

    @Override
    public Set<Integer> getArticles(Integer userId) throws SQLException {

        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT Id FROM Articles WHERE AuthorId = ?");

        //TODO CHECK INPUT

        query.setInt(1, userId);

        ResultSet result = query.executeQuery();

        Set<Integer> articles = new HashSet<>();
        while (result.next()) {
            articles.add(result.getInt("Id"));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return articles;
    }
}
