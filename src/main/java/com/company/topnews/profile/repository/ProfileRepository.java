package com.company.topnews.profile.repository;

import com.company.topnews.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);


    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);

    boolean existsByEmail(String phone);


    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity s SET s.status = 'ACTIVE' WHERE s.phone=?1 ")
    Integer update(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProfileRoleEntity pr WHERE pr.profileId = :profileId")
    void deleteRolesByProfileId(@Param("profileId") Integer profileId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProfileEntity p WHERE p.id = :profileId")
    void deleteProfileById(@Param("profileId") Integer profileId);

}
