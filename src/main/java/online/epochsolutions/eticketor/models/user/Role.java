package online.epochsolutions.eticketor.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static online.epochsolutions.eticketor.models.user.Permission.*;

@RequiredArgsConstructor
public enum Role {
  USER(
          Set.of(
              USER_CREATE,
              USER_READ
          )
  ),

ADMIN(
        Set.of(
                ADMIN_READ,
                ADMIN_CREATE,
                ADMIN_UPDATE,
                ADMIN_DELETE,

                HOST_READ,
                HOST_CREATE,
                HOST_UPDATE,
                HOST_DELETE
        )
)
,HOST(
        Set.of(
                HOST_READ,
                HOST_CREATE,
                HOST_UPDATE,
                HOST_DELETE
        )
)

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}