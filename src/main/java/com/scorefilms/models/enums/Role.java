package com.scorefilms.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Владелец площадки"),
    MANAGER("Менеджер площадки"),
    USER("Пользователь");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}
