package healthblog.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcConnection {
    Connection getConnection() throws SQLException;

    void closeConnection(Connection con) throws SQLException;

    PreparedStatement getPreparedStatement(Connection con, String statement) throws SQLException;

    void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException;

    int executeUpdate(PreparedStatement query) throws SQLException;

    ResultSet executeQuery(PreparedStatement query) throws SQLException;

    void closeResultSet(ResultSet resultSet) throws SQLException;
}
