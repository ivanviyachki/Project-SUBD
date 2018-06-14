package healthblog.services;

import healthblog.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("tagService")
public class TagServiceStubImpl implements TagService {
    @Autowired
    private JdbcConnection jdbcConnection;
    @Autowired
    private TagService tagService;

    public TagServiceStubImpl() throws SQLException {
    }

    @Override
    public Tag findTag(String name) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tags WHERE Name = ?");

        query.setString( 1, name);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        String Name = result.getString("Name");

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return new Tag(Name);
    }

    @Override
    public Tag findById(Integer id) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tags WHERE Id = ?");

        query.setInt( 1, id);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        String Name = result.getString("Name");

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return new Tag(Name);
    }

    @Override
    public void createTag(Tag tag) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("INSERT INTO Tags(Name) VALUES (?)");

        query.setString( 1, tag.getName());

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public void updateTag(String name, Tag tag) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("UPDATE Tag SET Name = ? WHERE Name = ?");

        query.setString( 1, tag.getName());
        query.setString( 2, name);

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public void deleteArticle(String name) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("DELETE FROM Tag WHERE Name = ?");

        query.setString( 1, name);

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public List<Tag> getAllTags() throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM Tags");

        ResultSet result = this.jdbcConnection.executeQuery(query);

        List<Tag> tags = new ArrayList<>();
        while (result.next()) {
            Integer resultId = result.getInt("Id");
            String Name = result.getString("Name");

            Set<Integer> resultArticles = getArticles(resultId);

            tags.add(new Tag(Name, resultArticles));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return tags;
    }

    @Override
    public Set<Integer> getArticles(Integer TagId) throws SQLException {

        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT ArticleId FROM Articles_Tags WHERE TagId = ?");

        query.setInt(1, TagId);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Set<Integer> articles = new HashSet<>();
        while (result.next()) {
            articles.add(result.getInt("ArticleId"));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return articles;
    }

    @Override
    public Set<Integer> getTags(Integer ArticleId) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        PreparedStatement query = con.prepareStatement("SELECT TagId FROM Articles_Tags WHERE ArticleId = ?");

        query.setInt(1, ArticleId);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Set<Integer> users = new HashSet<>();
        while (result.next()) {
            users.add(result.getInt("TagId"));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return users;
    }
}
