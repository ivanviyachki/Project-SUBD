package healthblog.services;

import healthblog.models.Article;
import healthblog.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Autowired
    private JdbcConnection jdbcConnection;

    @Autowired
    private ArticleService articleService;

    public void createImage(Image image) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "INSERT INTO Images(Path, ArticleId) VALUES (?, ?)";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setString( 1, image.getPath());
        query.setInt( 2, image.getArticle().getId());

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    public List<Image> getAllImages() throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Images";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        ResultSet result = this.jdbcConnection.executeQuery(query);

        List<Image> images = new ArrayList<>();
        while (result.next()) {
            Integer imageId = result.getInt("Id");
            String path = result.getString("Path");
            int articleId = result.getInt("ArticleId");

            Article article = this.articleService.findById(articleId);

            images.add(new Image(imageId, path, article));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return images;
    }

    public List<Image> getAllImagesFromArticle(Article article) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Images WHERE ArticleId = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setInt( 1, article.getId());

        ResultSet result = this.jdbcConnection.executeQuery(query);

        List<Image> images = new ArrayList<>();
        while (result.next()) {
            Integer imageId = result.getInt("Id");
            String path = result.getString("Path");
            int articleId = result.getInt("ArticleId");

            images.add(new Image(imageId, path, article));
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return images;
    }

    public Image findImage(String path) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Images WHERE Path = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setString(1, path);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Integer imageId = result.getInt("Id");
        String resultPath = result.getString("Path");
        int articleId = result.getInt("ArticleId");

        Article article = this.articleService.findById(articleId);

        Image image = new Image(imageId, resultPath, article);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return image;
    }

    public Image findById(Integer id) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Images WHERE Id = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setInt( 1, id);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Integer imageId = result.getInt("Id");
        String path = result.getString("Path");
        int articleId = result.getInt("ArticleId");

        Article article = this.articleService.findById(articleId);

        Image image = new Image(imageId, path, article);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return image;
    }

    public void updateImage(Image image, String path) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "UPDATE Images SET Path = ?, ArticleId = ? WHERE Path = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setString( 3, image.getPath());

        //TODO CHECK INPUT

        query.setString( 1, path);
        query.setInt(2, image.getArticle().getId());

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    public void deleteImage(Image image) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "DELETE FROM Articles WHERE Path = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setString( 1, image.getPath());

        //TODO CHECK INPUT

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }
}
