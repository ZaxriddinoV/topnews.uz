package com.company.topnews.reference.category.service;

import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.reference.category.dto.CategoryDTO;
import com.company.topnews.reference.category.dto.LangDTO;
import com.company.topnews.reference.category.entity.CategoryEntity;
import com.company.topnews.reference.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryEntity addCategory(CategoryDTO dto) {
        Optional<CategoryEntity> byNameEn = repository.findByNameEnAndVisibleTrue(dto.getNameEn());
        if (byNameEn.isPresent() && byNameEn.get().getNameUz().equals(dto.getNameUz())) {
            throw new AppBadException("category already exist");
        } else {
            return repository.save(dtoToEntity(dto));
        }
    }

    public CategoryEntity updateCategory(CategoryDTO region, Integer id) {
        Optional<CategoryEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            return repository.save(dtoToEntity(region));
        } else {
            throw new AppBadException("category does not exist");
        }
    }

    public String deleteCategory(Integer id) {
        Optional<CategoryEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            byId.get().setVisible(Boolean.FALSE);
            repository.save(byId.get());
            return "Deleted category";
        } else {
            throw new AppBadException("category does not exist");
        }
    }

    private CategoryEntity dtoToEntity(CategoryDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNameEn(dto.getNameEn());
        categoryEntity.setNameUz(dto.getNameUz());
        categoryEntity.setNameRu(dto.getNameRu());
        categoryEntity.setOrderNumber(dto.getOrderNumber());
        categoryEntity.setKey(dto.getKey());
        categoryEntity.setVisible(Boolean.TRUE);
        categoryEntity.setCreatedDate(LocalDateTime.now());
        return categoryEntity;
    }

    public List<CategoryEntity> categoryAll() {
        List<CategoryEntity> allByVisibleTrue = repository.findAllByVisibleTrue();
        if (!allByVisibleTrue.isEmpty()) {
            return allByVisibleTrue;
        } else throw new AppBadException("category does not exist");
    }

    public List<LangDTO> categoryLang(String lang) {
        if (lang.equals("en")) {
            List<LangDTO> regionsInEn = repository.getRegionsInEn();
            if (!regionsInEn.isEmpty()) {
                return regionsInEn;
            } else {
                throw new AppBadException("category does not exist");
            }
        } else if (lang.equals("ru")) {
            List<LangDTO> regionsInRu = repository.getRegionsInRu();
            if (!regionsInRu.isEmpty()) {
                return regionsInRu;
            } else {
                throw new AppBadException("category does not exist");
            }
        } else if (lang.equals("uz")) {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("category does not exist");
            }
        } else {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("category does not exist");
            }
        }

    }
}
