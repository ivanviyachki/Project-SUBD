package healthblog.services;

import healthblog.models.Article;
import healthblog.models.Image;

import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    void createImage(Image image) throws SQLException;

    List<Image> getAllImages() throws SQLException;

    List<Image> getAllImagesFromArticle(Article article) throws SQLException;

    Image findImage(String path) throws SQLException;

    Image findById(Integer id) throws SQLException;

    void updateImage(Image image, String path)throws SQLException;

    void deleteImage(Image image) throws SQLException;
}
