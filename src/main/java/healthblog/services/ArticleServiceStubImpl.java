package healthblog.services;

import healthblog.models.Article;
import healthblog.models.Image;
import healthblog.models.Tag;
import healthblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("articleService")
public class ArticleServiceStubImpl implements ArticleService {
    @Autowired
    private JdbcConnection jdbcConnection;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TagService tagService;

    public void createArticle(Article article) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "INSERT INTO Articles(Category, Title, Content, AuthorId, Date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setString( 1, article.getCategory());
        query.setString( 2, article.getTitle());
        query.setString(3, article.getContent());
        query.setInt( 4, article.getAuthor().getId());
        query.setDate( 5, new java.sql.Date(article.getDate().getTime()));

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        for(Image image : article.getImages()) {
            this.imageService.createImage(image);
        }

        for(Tag tag : article.getTags()) {
            this.tagService.createTag(tag);
        }

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    public List<Article> getAllArticles() throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Articles";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        ResultSet result = this.jdbcConnection.executeQuery(query);

        List<Article> articles = new ArrayList<>();
        while (result.next()) {
            Integer articleId = result.getInt("Id");
            String category = result.getString("Category");
            String title = result.getString("Title");
            String content = result.getString("Content");
            int authorId = result.getInt("AuthorId");
            Date date = result.getDate("Date");

            User user = this.userService.findById(authorId);

            Set<Integer> resultRoles = this.tagService.getTags(articleId);

            Article article = new Article(articleId, category, title, content, user, date, resultRoles);

            List<Image> images = this.imageService.getAllImagesFromArticle(article);
            article.setImages(images);

            articles.add(article);
        }

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return articles;
    }

    public Article findArticle(String title) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Articles WHERE Title = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setString( 1, title);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Integer articleId = result.getInt("Id");
        String category = result.getString("Category");
        String resultTitle = result.getString("Title");
        String content = result.getString("Content");
        int authorId = result.getInt("AuthorId");
        Date date = result.getDate("Date");

        User user = this.userService.findById(authorId);

        Set<Integer> resultRoles = this.tagService.getTags(articleId);

        Article article = new Article(articleId, category, title, content, user, date, resultRoles);

        List<Image> images = this.imageService.getAllImagesFromArticle(article);
        article.setImages(images);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return article;
    }

    public Article findById(int id) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "SELECT * FROM Articles WHERE Id = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        //TODO CHECK INPUT

        query.setInt( 1, id);

        ResultSet result = this.jdbcConnection.executeQuery(query);

        Integer articleId = result.getInt("Id");
        String category = result.getString("Category");
        String resultTitle = result.getString("Title");
        String content = result.getString("Content");
        int authorId = result.getInt("AuthorId");
        Date date = result.getDate("Date");

        User user = this.userService.findById(authorId);

        Set<Integer> resultRoles = this.tagService.getTags(articleId);

        Article article = new Article(articleId, category, resultTitle, content, user, date, resultRoles);

        List<Image> images = this.imageService.getAllImagesFromArticle(article);
        article.setImages(images);

        this.jdbcConnection.closeResultSet(result);
        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);

        return article;
    }

    public void updateArticle(Article article, String title) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "UPDATE Articles SET Category = ?, Title = ?, Content = ?, AuthorId = ?, Date = ? WHERE Title = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setString( 6, article.getTitle());

        //TODO CHECK INPUT

        query.setString( 1, article.getCategory());
        query.setString( 2, title);
        query.setString(3, article.getContent());
        query.setInt( 4, article.getAuthor().getId());
        query.setDate( 5, new java.sql.Date(article.getDate().getTime()));

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }

    public void deleteArticle(Article article) throws SQLException {
        Connection con = this.jdbcConnection.getConnection();
        String statement = "DELETE FROM Articles WHERE Title = ?";
        PreparedStatement query = this.jdbcConnection.getPreparedStatement(con, statement);

        query.setString( 1, article.getTitle());

        //TODO CHECK INPUT

        int affectedRows = this.jdbcConnection.executeUpdate(query);

        this.jdbcConnection.closePreparedStatement(query);
        this.jdbcConnection.closeConnection(con);
    }
}
