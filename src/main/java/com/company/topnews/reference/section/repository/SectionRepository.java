package com.company.topnews.reference.section.repository;

import com.company.topnews.reference.section.dto.LangDTO;
import com.company.topnews.reference.section.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Integer> {

    Optional<SectionEntity> findByNameEnAndVisibleTrue(String nameEn);
    Optional<SectionEntity> findByIdAndVisibleTrue(Integer id);
    List<SectionEntity> findAllByVisibleTrue();

    @Query("SELECT new com.company.topnews.reference.section.dto.LangDTO(r.id,r.key,r.nameRu) FROM SectionEntity r")
    List<LangDTO> getRegionsInRu();
    @Query("SELECT new com.company.topnews.reference.section.dto.LangDTO(r.id,r.key,r.nameUz) FROM SectionEntity r")
    List<LangDTO> getRegionsInUz();
    @Query("SELECT new com.company.topnews.reference.section.dto.LangDTO(r.id,r.key,r.nameEn) FROM SectionEntity r")
    List<LangDTO> getRegionsInEn();


}
