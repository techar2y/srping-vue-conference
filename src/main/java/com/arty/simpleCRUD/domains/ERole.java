package com.arty.simpleCRUD.domains;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public enum ERole {
    ROLE_USER,
    ROLE_PRESENTER,
    ROLE_ADMIN
    /*ROLE_USER(Set.of(EPermission.PRESENTATION_READ, EPermission.SCHEDULE_READ, EPermission.ROOM_READ)),
    ROLE_PRESENTER(Set.of(EPermission.PRESENTATION_READ, EPermission.PRESENTATION_WRITE,
            EPermission.SCHEDULE_READ)),
    ROLE_ADMIN(Set.of(EPermission.USER_READ, EPermission.USER_WRITE,
            EPermission.PRESENTATION_READ, EPermission.PRESENTATION_WRITE,
            EPermission.ROOM_READ, EPermission.ROOM_WRITE,
            EPermission.SCHEDULE_READ, EPermission.SCHEDULE_WRITE));

    Set<EPermission> permissionSet;

    ERole(Set<EPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<EPermission> getPermissionSet ()
    {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }*/
}
