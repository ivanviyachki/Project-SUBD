package healthblog.services;

import healthblog.models.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    boolean articleExists(Integer id);

    Article findArticle(Integer id);

    void deleteArticle(Article article);

    void saveArticle(Article article) throws SQLException;
}
