package com.app.grievance.repository;
import com.app.grievance.model.Role;
import com.app.grievance.model.RoleMapping;
import com.app.grievance.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface RoleMappingRepository extends JpaRepository<RoleMapping, Long>
{
    public Optional<RoleMapping> findByRole(Role role);
}
