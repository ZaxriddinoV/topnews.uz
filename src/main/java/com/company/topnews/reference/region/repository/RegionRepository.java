package com.company.topnews.reference.region.repository;

import com.company.topnews.reference.region.dto.LangDTO;
import com.company.topnews.reference.region.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    Optional<RegionEntity> findByNameEnAndVisibleTrue(String nameEn);
    Optional<RegionEntity> findByIdAndVisibleTrue(Integer id);
    List<RegionEntity> findAllByVisibleTrue();

    @Query("SELECT new com.company.topnews.reference.region.dto.LangDTO(r.id,r.key,r.nameRu) FROM RegionEntity r")
    List<LangDTO> getRegionsInRu();
    @Query("SELECT new com.company.topnews.reference.region.dto.LangDTO(r.id,r.key,r.nameUz) FROM RegionEntity r")
    List<LangDTO> getRegionsInUz();
    @Query("SELECT new com.company.topnews.reference.region.dto.LangDTO(r.id,r.key,r.nameEn) FROM RegionEntity r")
    List<LangDTO> getRegionsInEn();


}
