package com.imbuka.blogging_platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name = "Comments",
        description = "Schema to hold Comments Information"
)
public class CommentDTO {

    @NotEmpty(message = "Blog id can not be empty or null, Kindly input blog id")
    @Schema(
            description = "Blog Id for blog Posts",
            example = "blog_id = 1 (Representing id of the blog created)"
    )
    private String blog_id;

    @Schema(
            description = "Comments on the blog Posts",
            example = "Amazing piece on the Java Architecture, very informative"
    )
    @NotEmpty(message = "Comment content cannot be empty, Kindly input comments")
    private String commentContent;
}
