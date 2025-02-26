package healthblog.models;

import java.util.*;

public class Article {
    private Integer id;

    private String category;

    private String title;

    private String content;

    private User author;

    private List<Image> images;

    private List<Comment> comments;

    private Date date = new Date();

    private List<Tag> tags;

    private Set<Integer> tagsId;

    public Article() {   }

    public Article(String category, String title, String content, User author, Date date) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.tags = new ArrayList<>();
        this.images = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tagsId = new LinkedHashSet<>();
    }

    public Article(Integer id, String category, String title, String content, User author, Date date, Set<Integer> tagsId) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
        this.tags = new ArrayList<>();
        this.images = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tagsId = tagsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getSummary(){
        int endIndex = this.getContent().length() / 2;

        return this.getContent().substring(0, endIndex) + "...";
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Integer> getTagsId() {
        return tagsId;
    }

    public void setTagsId(Set<Integer> tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != null ? !id.equals(article.id) : article.id != null) return false;
        if (category != null ? !category.equals(article.category) : article.category != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (content != null ? !content.equals(article.content) : article.content != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        if (images != null ? !images.equals(article.images) : article.images != null) return false;
        if (comments != null ? !comments.equals(article.comments) : article.comments != null) return false;
        if (date != null ? !date.equals(article.date) : article.date != null) return false;
        if (tags != null ? !tags.equals(article.tags) : article.tags != null) return false;
        return tagsId != null ? tagsId.equals(article.tagsId) : article.tagsId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (tagsId != null ? tagsId.hashCode() : 0);
        return result;
    }
}

