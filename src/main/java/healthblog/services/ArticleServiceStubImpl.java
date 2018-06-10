package healthblog.services;

import healthblog.models.Article;
import healthblog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        return articleRepository.findAll();
    }

    public boolean articleExists(Integer id) {
        return articleRepository.exists(id);
    }

    public Article findArticle(Integer id) {
        return articleRepository.findOne(id);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    public void saveArticle(Article article) throws SQLException {
        PreparedStatement query = con.prepareStatement("INSERT INTO Articles(Id, Name) VALUES (?, ?)");

        articleRepository.saveAndFlush(article);
    }
}
