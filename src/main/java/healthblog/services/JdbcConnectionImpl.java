package healthblog.services;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class JdbcConnectionImpl implements JdbcConnection {
    private static String connectionString = "jdbc:mysql://localhost:3306/health_blog?createDatabaseIfNotExist=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }

    @Override
    public void closeConnection(Connection con) throws SQLException {
        con.close();
    }

    @Override
    public PreparedStatement getPreparedStatement(Connection con, String statement) throws SQLException {
        return con.prepareStatement(statement);
    }

    @Override
    public void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    @Override
    public int executeUpdate(PreparedStatement query) throws SQLException {
        return query.executeUpdate();
    }

    @Override
    public ResultSet executeQuery(PreparedStatement query) throws SQLException {
        return query.executeQuery();
    }

    @Override
    public void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
