package online.epochsolutions.eticketor.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    HOST_READ("host:read"),
    HOST_UPDATE("host:update"),
    HOST_CREATE("host:create"),
    HOST_DELETE("host:delete"),
    
//    ,
//
//
//    MANAGER_READ("manager:read"),
//    MANAGER_UPDATE("manager:update"),
//    MANAGER_CREATE("manager:create"),
//    MANAGER_DELETE("manager:delete")

    USER_CREATE("user:create");

    @Getter
    private final String permission;
}
