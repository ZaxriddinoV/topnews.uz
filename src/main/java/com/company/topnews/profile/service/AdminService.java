package com.company.topnews.profile.service;

import com.company.topnews.profile.dto.AdminCreateDTO;
import com.company.topnews.profile.dto.ProfileInfoDTO;
import com.company.topnews.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileInfoDTO adminCreate(AdminCreateDTO dto) {

        return null;
    }
}
