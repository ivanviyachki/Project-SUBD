package healthblog.models;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private Integer id;

    private String name;

    private Set<Integer> usersId;

    public Role(Integer id, String name, Set<Integer> usersId) {
        this.id = id;
        this.name = name;
        this.usersId = usersId;
    }

    public Role() {
        this.usersId = new HashSet<>();
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

    public Set<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<Integer> usersId) {
        this.usersId = usersId;
    }
}