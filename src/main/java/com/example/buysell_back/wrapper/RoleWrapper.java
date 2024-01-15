package com.example.buysell_back.wrapper;

import com.example.buysell_back.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class RoleWrapper implements GrantedAuthority {

    private Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
