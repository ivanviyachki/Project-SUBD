package healthblog.models;

public class Comment {
    private Integer id;

    private String content;

    private Article article;

    public Comment(Integer id, String content, Article article) {
        this.id = id;
        this.content = content;
        this.article = article;
    }

    public Comment() { }

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

    public String getContent(){ return content;}

    public void setContent(String content){this.content = content;}

}
