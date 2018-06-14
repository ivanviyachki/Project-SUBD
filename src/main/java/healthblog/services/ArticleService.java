package healthblog.services;

import healthblog.models.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleService {
    void createArticle(Article article) throws SQLException;

    List<Article> getAllArticles() throws SQLException;

    Article findArticle(String title)throws SQLException;

    Article findById(int id) throws SQLException;

    void updateArticle(Article article, String title)throws SQLException;

    void deleteArticle(Article article)throws SQLException;
}
