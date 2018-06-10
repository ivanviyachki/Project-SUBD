package healthblog.services;

import healthblog.models.Image;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
    String connectionString = "jdbc:mysql://localhost:3306/health_blog?createDatabaseIfNotExist=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
    Connection con = DriverManager.getConnection(connectionString);

    public ImageServiceImpl() throws SQLException {
    }

    public List<Image> getAllImages() {
        //return this.imageRepository.findAll();
        return null; //TODO
    }

    public boolean imageExists(Integer id) {
        //return this.imageRepository.exists(id);
        return false; //TODO
    }

    public Image findImage(String path) {
        //return this.imageRepository.findByPath(path);
        return null; //TODO
    }

    public void deleteImage(Image image) {
        //this.imageRepository.delete(image);
        //TODO
    }

    public void saveImage(Image image) {
        //this.imageRepository.saveAndFlush(image);
        //TODO
    }
}
