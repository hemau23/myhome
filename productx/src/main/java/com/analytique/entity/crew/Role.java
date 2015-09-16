package com.analytique.entity.crew;

import org.springframework.data.annotation.Id;

public class Role {

    @Id
    Long rolId;
    String roleName;

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
