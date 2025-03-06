package com.company.topnews.reference.category.repository;

import com.company.topnews.reference.category.dto.LangDTO;
import com.company.topnews.reference.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByNameEnAndVisibleTrue(String nameEn);
    Optional<CategoryEntity> findByIdAndVisibleTrue(Integer id);
    List<CategoryEntity> findAllByVisibleTrue();

    @Query("SELECT new com.company.topnews.reference.category.dto.LangDTO(r.id,r.key,r.nameRu) FROM CategoryEntity r")
    List<LangDTO> getRegionsInRu();
    @Query("SELECT new com.company.topnews.reference.category.dto.LangDTO(r.id,r.key,r.nameUz) FROM CategoryEntity r")
    List<LangDTO> getRegionsInUz();
    @Query("SELECT new com.company.topnews.reference.category.dto.LangDTO(r.id,r.key,r.nameEn) FROM CategoryEntity r")
    List<LangDTO> getRegionsInEn();


}
