package com.company.topnews.profile.repository;

import com.company.topnews.profile.entity.ProfileRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity, Integer> {

}
