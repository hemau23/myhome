package com.analytique.entity.crew;

import org.springframework.data.annotation.Id;

public class Role {

    @Id
    String rolId;
    String roleName;

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
