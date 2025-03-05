package com.company.topnews.profile.dto;


import com.company.topnews.attach.entity.AttachEntity;
import com.company.topnews.profile.entity.ProfileEntity;
import com.company.topnews.profile.entity.ProfileRoleEntity;
import com.company.topnews.profile.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotBlank(message = "Name not found")
    private String name;
    @NotBlank(message = "Surname not found")
    private String surname;
    @NotBlank(message = "Email not found")
    private String email;
    @NotBlank(message = "Password not found")
    private String password;
    private AttachEntity photo;
    private ProfileStatus status;
    private List<ProfileRoleEntity> role;
    private String JwtToken;
    private String refreshToken;

    public ProfileEntity mapToEntity() {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(this.getName());
        profileEntity.setSurname(this.getSurname());
        profileEntity.setPassword(this.getPassword());
        profileEntity.setRole(this.getRole());
        profileEntity.setEmail(this.getEmail());
        profileEntity.setStatus(this.getStatus());
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileEntity.setVisible(Boolean.TRUE);
        return profileEntity;
    }
}
