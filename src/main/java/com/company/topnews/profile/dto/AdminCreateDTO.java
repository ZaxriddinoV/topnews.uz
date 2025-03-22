package com.company.topnews.profile.dto;

import com.company.topnews.profile.enums.ProfileRole;
import com.company.topnews.profile.enums.ProfileStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminCreateDTO {
    @NotBlank(message = "name not blank")
    private String name;
    @NotBlank(message = "surname not blank")
    private String surname;
    @NotBlank(message = "username not Blank")
    private String username;
    @NotBlank(message = "password not blank")
    private String password;
    private ProfileStatus status;
    private List<ProfileRole> roleList;
}
