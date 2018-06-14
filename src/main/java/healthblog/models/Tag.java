package healthblog.models;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private Integer id;

    private String name;

    private List<Article> articles;

    public Tag() {  }

    public Tag(String name) {
        this.name = name;
        this.articles = new ArrayList<Article>();
    }

    public Tag(String name, Integer id) {
        this.id = id;
        this.name = name;
        this.articles = new ArrayList<Article>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return articles != null ? articles.equals(tag.articles) : tag.articles == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }
}
