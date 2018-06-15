package healthblog.services;

import healthblog.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import healthblog.models.User;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("blogUserDetailsService")
public class BlogUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserService userService;
    @Autowired
    private RoleService roleService;

    public BlogUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            Set<Role> userRoles = new LinkedHashSet<>();

            try {
                for(Integer roleId : this.userService.getRoles(user.getId())) {
                    userRoles.add(this.roleService.findById(roleId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Set<GrantedAuthority> grantedAuthorities = userRoles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }
}
