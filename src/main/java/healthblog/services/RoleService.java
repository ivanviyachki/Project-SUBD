package healthblog.services;

import healthblog.models.Role;

import java.sql.SQLException;
import java.util.Set;

public interface RoleService {
    Role findById(Integer id) throws SQLException;

    Role findByName(String name) throws SQLException;

    Set<Integer> getUsers(Integer roleId) throws SQLException;
}
