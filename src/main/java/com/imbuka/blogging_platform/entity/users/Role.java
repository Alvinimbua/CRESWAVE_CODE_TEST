package com.imbuka.blogging_platform.entity.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.imbuka.blogging_platform.entity.users.Permissions.*;

@RequiredArgsConstructor

public enum Role {

    //user has no permissions
    NORMAL_USER(
            Set.of(
                    NORMAL_USER_READ,
                    NORMAL_USER_CREATE,
                    NORMAL_USER_UPDATE,
                    NORMAL_USER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    NORMAL_USER_READ,
                    NORMAL_USER_CREATE,
                    NORMAL_USER_UPDATE,
                    NORMAL_USER_DELETE
            )
    );
    @Getter
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
       var authorities = getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermissions()))
                .collect(Collectors.toList());
       authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

       return authorities;
    }

}
