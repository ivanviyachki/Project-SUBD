package healthblog.models;

public class Comment {
    private Integer id;

    private Article article;

    public Comment() { }

    public Comment(Article article) {
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){ this.id = id;}

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
