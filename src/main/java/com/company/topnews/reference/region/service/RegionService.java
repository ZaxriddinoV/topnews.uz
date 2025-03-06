package com.company.topnews.reference.region.service;

import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.reference.region.dto.LangDTO;
import com.company.topnews.reference.region.dto.RegionDTO;
import com.company.topnews.reference.region.entity.RegionEntity;
import com.company.topnews.reference.region.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository repository;

    public RegionEntity addRegion(RegionDTO region) {
        Optional<RegionEntity> byNameEn = repository.findByNameEnAndVisibleTrue(region.getNameEn());
        if (byNameEn.isPresent() && byNameEn.get().getNameUz().equals(region.getNameUz())) {
            throw new AppBadException("region already exist");
        } else {
            return repository.save(dtoToEntity(region));
        }
    }

    public RegionEntity updateRegion(RegionDTO region, Integer id) {
        Optional<RegionEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            return repository.save(dtoToEntity(region));
        } else {
            throw new AppBadException("region does not exist");
        }
    }

    public String deleteRegion(Integer id) {
        Optional<RegionEntity> byId = repository.findByIdAndVisibleTrue(id);
        if (byId.isPresent()) {
            byId.get().setVisible(Boolean.FALSE);
            repository.save(byId.get());
            return "Deleted region";
        } else {
            throw new AppBadException("region does not exist");
        }
    }

    private RegionEntity dtoToEntity(RegionDTO dto) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setNameEn(dto.getNameEn());
        regionEntity.setNameUz(dto.getNameUz());
        regionEntity.setNameRu(dto.getNameRu());
        regionEntity.setOrderNumber(dto.getOrderNumber());
        regionEntity.setKey(dto.getKey());
        regionEntity.setVisible(Boolean.TRUE);
        regionEntity.setCreatedDate(LocalDateTime.now());
        return regionEntity;
    }

    public List<RegionEntity> regionAll() {
        List<RegionEntity> allByVisibleTrue = repository.findAllByVisibleTrue();
        if (!allByVisibleTrue.isEmpty()) {
            return allByVisibleTrue;
        } else throw new AppBadException("region does not exist");
    }

    public List<LangDTO> regionLang(String lang) {
        if (lang.equals("en")) {
            List<LangDTO> regionsInEn = repository.getRegionsInEn();
            if (!regionsInEn.isEmpty()) {
                return regionsInEn;
            } else {
                throw new AppBadException("region does not exist");
            }
        } else if (lang.equals("ru")) {
            List<LangDTO> regionsInRu = repository.getRegionsInRu();
            if (!regionsInRu.isEmpty()) {
                return regionsInRu;
            } else {
                throw new AppBadException("region does not exist");
            }
        } else if (lang.equals("uz")) {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("region does not exist");
            }
        } else {
            List<LangDTO> regionsInUz = repository.getRegionsInUz();
            if (!regionsInUz.isEmpty()) {
                return regionsInUz;
            } else {
                throw new AppBadException("region does not exist");
            }
        }

    }
}
