package com.company.topnews.profile.service;



import com.company.topnews.exceptionHandler.AppBadException;
import com.company.topnews.profile.dto.AuthDTO;
import com.company.topnews.profile.dto.ProfileDTO;
import com.company.topnews.profile.dto.RegistrationDTO;
import com.company.topnews.profile.entity.ProfileEntity;
import com.company.topnews.profile.entity.ProfileRoleEntity;
import com.company.topnews.profile.enums.ProfileRole;
import com.company.topnews.profile.enums.ProfileStatus;
import com.company.topnews.profile.enums.UsernameEnum;
import com.company.topnews.profile.repository.ProfileRepository;
import com.company.topnews.profile.repository.ProfileRoleRepository;
import com.company.topnews.usernameHistory.dto.SmsConfirmDTO;
import com.company.topnews.usernameHistory.entiy.SmsHistoryEntity;
import com.company.topnews.usernameHistory.repository.SmsHistoryRepository;
import com.company.topnews.usernameHistory.service.EmailHistoryService;
import com.company.topnews.usernameHistory.service.EmailSendingService;
import com.company.topnews.usernameHistory.service.SmsHistoryService;
import com.company.topnews.usernameHistory.service.SmsService;
import com.company.topnews.util.JwtUtil;
import com.company.topnews.util.MD5Util;
import com.company.topnews.util.UsernameValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Value("${server.domain}")
    private String domainName;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    SmsService smsService;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    SmsHistoryService historyService;
    @Autowired
    SmsHistoryRepository smsHistoryRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        if (optional.isPresent() && optional.get().getStatus().equals(ProfileStatus.IN_REGISTRATION)) {
            ProfileEntity profileEntity = optional.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime createdDate = profileEntity.getCreatedDate();
            Duration duration = Duration.between(createdDate, now);
            if (duration.toMinutes() < 1) {
                System.out.println("Sizga emailga link yuborilgan!");
            } else {
                profileRepository.delete(profileEntity);
            }
        }

        if (optional.isPresent() && optional.get().getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Email or Phone already exists");
        }
        UsernameValidationUtil util = new UsernameValidationUtil();
        UsernameEnum usernameEnum = util.identifyInputType(dto.getEmail());
        switch (usernameEnum) {
            case PHONE_NUMBER:
                ProfileEntity profilePhone = registerConvert(dto);
                Integer code = smsService.sendRegistrationSms(dto.getEmail());
                System.out.println("Code == " + code);
                historyService.smsHistory(profilePhone.getEmail(), code, profilePhone.getCreatedDate());
                return "Confirm sent sms";
            case EMAIL:
                ProfileEntity profileEmail = registerConvert(dto);
                StringBuilder sb = new StringBuilder();
                sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
                sb.append("<br>");
                sb.append("<p>Click the link below to complete registration</p>\n");
                sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=").append(domainName).append("/api/auth/registration/confirm/")
                        .append(profileEmail.getId()).append(" target=_blank >Click Th</a></p>\n");

                emailSendingService.sendSimpleMessage(dto.getEmail(), "Complite Registration", sb.toString());
                emailSendingService.sendMimeMessage(dto.getEmail(), "Tasdiqlash", sb.toString());
                emailHistoryService.addEmailHistory(dto.getEmail(), "Tasdiqlash", profileEmail.getCreatedDate());
                return "Confirm sent email";
            case UNKNOWN:
                throw new AppBadException("Invalid username");
        }
        throw new AppBadException("Invalid username");
    }

    public ProfileEntity registerConvert(RegistrationDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.md5(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.IN_REGISTRATION);
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return entity;
    }

    public String registrationConfirm(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Not Completed");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.IN_REGISTRATION)) {
            throw new AppBadException("Not Completed");
        }else {
            List<ProfileRoleEntity> roles = new ArrayList<>();
            entity.setStatus(ProfileStatus.ACTIVE);
            ProfileRoleEntity pRoleEntity = new ProfileRoleEntity();
            pRoleEntity.setProfileId(entity.getId());
            pRoleEntity.setRole(ProfileRole.ROLE_USER);
            roles.add(pRoleEntity);
            profileRoleRepository.save(pRoleEntity);
            entity.setRole(roles);
            profileRepository.save(entity);
            return "Completed";
        }
    }

    public String smsConfirm(SmsConfirmDTO dto, LocalDateTime dateTime) {
        boolean b = profileRepository.existsByEmail(dto.getPhone());
        if (b) {
            SmsHistoryEntity latestCodeByPhone = smsHistoryRepository.findLatestCodeByPhone(dto.getPhone());
            Duration duration = Duration.between(dateTime, latestCodeByPhone.getCreatedData());
            if (duration.abs().getSeconds() >= 120) {
                throw new AppBadException("SMS timed out");
            }
            if (latestCodeByPhone.getCode().equals(dto.getCode())) {
                profileRepository.update(dto.getPhone());
                return "Active account";
            } else {
                throw new AppBadException("the code is incorrect");
            }
        } else {
            throw new AppBadException("The phone number could not be found or it has already been registered");
        }
    }

    public ProfileDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getUsername());
        if (optional.isEmpty()) {
            throw new AppBadException("Email or Password wrong");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
            throw new AppBadException("Email or Password wrong");
        }
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("User Not Active");
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setRole(entity.getRole());
        String accessToken = JwtUtil.encode(entity.getEmail(), entity.getRole().toString());
        String refreshToken = JwtUtil.generateRefreshToken(entity.getEmail());
        profileDTO.setJwtToken(accessToken);
        profileDTO.setRefreshToken(refreshToken);
        return profileDTO;
    }


}
