package healthblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import healthblog.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}