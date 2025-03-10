package com.company.topnews.profile.service;

import com.company.topnews.profile.entity.ProfileRoleEntity;
import com.company.topnews.profile.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileRoleService {
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public List<String> getAllProfileRoles(List<ProfileRoleEntity> roleEntities) {
        List<String> roles = new ArrayList<>();
        for (ProfileRoleEntity roleEntity : roleEntities) {
            Optional<ProfileRoleEntity> byId = profileRoleRepository.findById(roleEntity.getId());
            byId.ifPresent(profileRoleEntity -> roles.add(profileRoleEntity.getRole().name()));
        }
        return roles;
    }
}
