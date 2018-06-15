import healthblog.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentsService {

    @Autowired
    private JdbcConnection jdbcConnection;

    @Autowired
    private ArticleService ArticleService;

    @Override
    public List<Comment> getAllComments() throws SQLException{

        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Comments";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        ResultSet result = this.jdbcConnection.executeQuery(query);



        List<Comment> comments = new ArrayList<>();

        while (result.next()) {
            Integer commentId = result.getInt("Id");
            String content = result.getString("Content");
            Article article = ArticleService.findById(result.getInt("ArticleId"));


            Comment comment = new Comment(commentId, content, article);
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return comments;
    }

    public List<Comment> getAllCommentsFromArticle(Article article) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Comments WHERE ArticleId = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setInt( 1, article.getId());

        ResultSet result = this.jdbcConnection.executeQuery(query);

        List<Comment> comments = new ArrayList<>();
        while (result.next()) {
            Integer commentId = result.getInt("Id");
            String content = result.getString("Content");

            comments.add(new Comment(commentId, content, article));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return comments;
    }

    @Override
    public Comment findComment(Integer id) throws SQLException {

        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Comments WHERE Id = ?\"";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);
        query.setInt(1,id);
        ResultSet result = this.jdbcConnection.executeQuery(query);

        Integer commentId = result.getInt("Id");
        String content = result.getString("Content");
        Article article = ArticleService.findById(result.getInt("ArticleId"));

        Comment comment = new Comment(commentId, content, article);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return comment;

    }

    @Override
    public void deleteComment(Comment comment)  throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "DELETE FROM Comments WHERE Id = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setInt(1,comment.getId());
        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    @Override
    public void saveComment(Comment comment) throws SQLException {

        Connection con = this.jdbcConnection.getConnection();
        String statement = "INSERT INTO Comments(ArticleId, Content) VALUES (?, ?)";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setInt( 1, comment.getArticle().getId());
        query.setString( 2, comment.getContent());

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

    }

    @Override
    public void updateComment(Comment comment)  throws SQLException{

        Connection con = this.jdbcConnection.getConnection();
        String statement = "UPDATE Comments SET ArticleId = ?, Content = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setInt( 1, comment.getArticle().getId());
        query.setString( 2, comment.getContent());

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }
}