package healthblog.services;

import healthblog.models.Article;
import healthblog.models.Tag;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface TagService {
    Tag findTag(String tag) throws SQLException;

    Tag findById(Integer id) throws SQLException;

    List<Tag> getAllTags() throws SQLException;

    void createTag(Tag tag) throws SQLException;

    void updateTag(String name, Tag tag) throws SQLException;

    void deleteArticle(String name) throws SQLException;

    Set<Integer> getArticles(Integer TagId) throws SQLException;

    Set<Integer> getTags(Integer ArticleId) throws SQLException;
}
