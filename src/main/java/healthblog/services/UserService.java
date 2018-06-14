package healthblog.services;

import healthblog.models.Role;
import healthblog.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface UserService {

    User findById(Integer id) throws SQLException;

    User findByEmail(String email) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    void createUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    Set<Integer> getRoles(Integer userId) throws SQLException;

    Set<Integer> getArticles(Integer userId) throws SQLException;
}
