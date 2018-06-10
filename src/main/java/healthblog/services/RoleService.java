package healthblog.services;

import healthblog.models.Role;

import java.sql.SQLException;

public interface RoleService {
    Role findRole(String name) throws SQLException;
}
