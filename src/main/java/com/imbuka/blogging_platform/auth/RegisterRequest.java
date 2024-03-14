package com.imbuka.blogging_platform.auth;

import com.imbuka.blogging_platform.entity.users.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema (
        name = "Register Request",
        description = "Schema to hold Register request Information"
)
public class RegisterRequest {
    @Schema(
            description = "First name of a User",
            example = "Alvin"
    )
    private String firstName;

    @Schema(
            description = "Last name of a User",
            example = "Twain"
    )
    private String lastName;

    @Schema(
            description = "Email of a User",
            example = "twain@gmail.com"
    )
    private String email;

    @Schema(
            description = "Password of a User",
            example = "123456qwty"
    )
    private String password;

    @Schema(
            description = "Role of the User",
            example = "ADMIN or NORMAL_USER"
    )
    private Role role;
}
