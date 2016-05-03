package com.analytique.repository.crew;

import com.analytique.entity.crew.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hemant on 9/22/2015.
 */
@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleRepository extends MongoRepository<Role,String> {

    Role findByRoleName(@Param("roleName") String roleName);
    Role findByRoleId(@Param("roleId") String roleId);

}
