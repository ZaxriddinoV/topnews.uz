package com.company.topnews.reference.section.service;

import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.reference.section.dto.LangDTO;
import com.company.topnews.reference.section.dto.SectionDTO;
import com.company.topnews.reference.section.entity.SectionEntity;
import com.company.topnews.reference.section.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public SectionEntity addSection(SectionDTO dto) {
        Optional<SectionEntity> byNameEn = repository.findByNameEnAndVisibleTrue(dto.getNameEn());
        if (byNameEn.isPresent() && byNameEn.get().getNameUz().equals(dto.getNameUz())) {
            throw new AppBadException("section already exist");
        } else {
            return repository.save(dtoToEntity(dto));
        }
    }

    public SectionEntity updateSection(SectionDTO region, Integer id) {
        Optional<SectionEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            return repository.save(dtoToEntity(region));
        } else {
            throw new AppBadException("section does not exist");
        }
    }

    public String deleteSection(Integer id) {
        Optional<SectionEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            byId.get().setVisible(Boolean.FALSE);
            repository.save(byId.get());
            return "Deleted section";
        } else {
            throw new AppBadException("section does not exist");
        }
    }

    private SectionEntity dtoToEntity(SectionDTO dto) {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setNameEn(dto.getNameEn());
        sectionEntity.setNameUz(dto.getNameUz());
        sectionEntity.setNameRu(dto.getNameRu());
        sectionEntity.setOrderNumber(dto.getOrderNumber());
        sectionEntity.setKey(dto.getKey());
        sectionEntity.setVisible(Boolean.TRUE);
        sectionEntity.setCreatedDate(LocalDateTime.now());
        return sectionEntity;
    }

    public List<SectionEntity> sectionAll() {
        List<SectionEntity> allByVisibleTrue = repository.findAllByVisibleTrue();
        if (!allByVisibleTrue.isEmpty()) {
            return allByVisibleTrue;
        } else throw new AppBadException("section does not exist");
    }

    public List<LangDTO> sectionLang(String lang) {
        if (lang.equals("en")) {
            List<LangDTO> regionsInEn = repository.getRegionsInEn();
            if (!regionsInEn.isEmpty()) {
                return regionsInEn;
            } else {
                throw new AppBadException("section does not exist");
            }
        } else if (lang.equals("ru")) {
            List<LangDTO> regionsInRu = repository.getRegionsInRu();
            if (!regionsInRu.isEmpty()) {
                return regionsInRu;
            } else {
                throw new AppBadException("section does not exist");
            }
        } else if (lang.equals("uz")) {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("section does not exist");
            }
        } else {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("section does not exist");
            }
        }

    }
}
