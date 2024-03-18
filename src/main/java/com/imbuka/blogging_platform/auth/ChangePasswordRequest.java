package com.imbuka.blogging_platform.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChangePasswordRequest {

    private String currentPassword;
    private String confirmationPassword;
    private String newPassword;
}
