package healthblog.models;

public class Image {
    private Integer id;

    private String path;

    private Article article;

    public Image() {    }

    public Image(String path, Article article) {
        this.path = path;
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
