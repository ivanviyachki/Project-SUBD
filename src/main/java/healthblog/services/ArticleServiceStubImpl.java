package healthblog.services;

import healthblog.models.Article;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service("articleService")
public class ArticleServiceStubImpl implements ArticleService {
    String connectionString = "jdbc:mysql://localhost:3306/health_blog?createDatabaseIfNotExist=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    Connection con = DriverManager.getConnection(connectionString);

    public ArticleServiceStubImpl() throws SQLException {
    }

    public List<Article> getAllArticles() {
        //return articleRepository.findAll();
        return null; //TODO
    }

    public boolean articleExists(Integer id) {
        //return articleRepository.exists(id);
        return false; //TODO
    }

    public Article findArticle(Integer id) {
        //return articleRepository.findOne(id);
        return null; //TODO
    }

    public void deleteArticle(Article article) {
        //articleRepository.delete(article);
        //TODO
    }

    public void saveArticle(Article article) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO Articles(Category, Title, AuthorId, Date) VALUES (?, ?, ?, ?)");

        //TODO CHECK INPUT

        query.setString( 1, article.getCategory());
        query.setString( 2, article.getTitle());
        query.setInt( 3, article.getAuthor().getId());
        query.setDate( 4, new java.sql.Date(article.getDate().getTime()));

        int affectedRows = query.executeUpdate();

    }
}
