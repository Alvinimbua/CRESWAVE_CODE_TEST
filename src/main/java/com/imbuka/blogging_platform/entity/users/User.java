package com.imbuka.blogging_platform.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "_users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private List<BlogPost> blogPosts;

//    @OneToMany(mappedBy = "author")
//    private List<BlogPost> blogPost;

    /**
     * a method that get a list of authorities depending on the role specified
     * @return list of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
