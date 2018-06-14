package healthblog.services;

import healthblog.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Service("roleService")
public class RoleServiceStubImpl implements RoleService {
    @Autowired
    private JdbcConnection jdbcConnection;

    public RoleServiceStubImpl() throws SQLException {
    }

    @Override
    public Role findById(Integer id) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Roles WHERE Id = ?");

        //TODO CHECK INPUT

        query.setInt( 1, id);

        ResultSet result = query.executeQuery();
        this.jdbcConnection.closePreparedStatement(query);

        String resultName = result.getString("Name");

        Set<Integer> usersId = getUsers(id);

        this.jdbcConnection.closeConnection(con);

        return new Role(id, resultName, usersId);
    }

    @Override
    public Role findByName(String name) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Roles WHERE Name = ?");

        //TODO CHECK INPUT

        query.setString( 1, name);

        ResultSet result = query.executeQuery();
        this.jdbcConnection.closePreparedStatement(query);

        Integer resultId = result.getInt("Id");

        Set<Integer> usersId = getUsers(resultId);

        this.jdbcConnection.closeConnection(con);

        return new Role(resultId, name, usersId);
    }

    @Override
    public Set<Integer> getUsers(Integer roleId) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT UserId FROM Users_Roles WHERE RoleId = ?");

        //TODO CHECK INPUT

        query.setInt(1, roleId);

        ResultSet result = query.executeQuery();
        this.jdbcConnection.closePreparedStatement(query);

        Set<Integer> users = new HashSet<>();
        while (result.next()) {
            users.add(result.getInt("UserId"));
        }

        this.jdbcConnection.closeConnection(con);

        return users;
    }
}
