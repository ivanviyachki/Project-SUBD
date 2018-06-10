package healthblog.services;

import healthblog.models.Role;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service("roleService")
public class RoleServiceStubImpl implements RoleService {
    String connectionString = "jdbc:mysql://localhost:3306/health_blog?createDatabaseIfNotExist=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    Connection con = DriverManager.getConnection(connectionString);

    public RoleServiceStubImpl() throws SQLException {
    }

    public Role findRole(String name) throws SQLException {

        PreparedStatement query = con.prepareStatement("SELECT * FROM Roles WHERE Name = ?");

        //TODO CHECK INPUT

        query.setString( 1, name);

        ResultSet result = query.executeQuery();

        String resultName = result.getString("Name");

        return new Role(resultName);
    }
}
