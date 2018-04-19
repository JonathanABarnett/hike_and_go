package com.alaythiaproductions.hike_and_go.repository;

import com.alaythiaproductions.hike_and_go.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
