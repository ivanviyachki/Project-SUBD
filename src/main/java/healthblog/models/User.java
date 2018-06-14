package healthblog.models;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class User {

    private Integer id;

    private String email;

    private String fullName;

    private String password;

    private Set<Integer> rolesId;

    private Set<Integer> articlesId;

    public User(Integer id, String email, String fullName, String password, Set<Integer> rolesId, Set<Integer> articlesId) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.rolesId = rolesId;
        this.articlesId = articlesId;
    }

    public User() {
        rolesId = new HashSet<>();
        articlesId = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Integer> getRolesId() {
        return rolesId;
    }

    public void setRolesId(Set<Integer> rolesId) {
        this.rolesId = rolesId;
    }

    public Set<Integer> getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(Set<Integer> articlesId) {
        this.articlesId = articlesId;
    }
}
