package com.imbuka.blogging_platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema (
        name = "Blog Posts",
        description = "Schema to hold Blog posts Information"
)
public class BlogPostDTO {
    @NotEmpty(message = "Message cannot be empty or null")
    @Schema(
            description = "Title for blog Posts",
            example = "Java Microservice Architecture"
    )
    private String title;
    @NotEmpty(message = "Content cannot be empty or null")
    @Schema(
            description = "Content for blog Posts",
            example = "How to spin up a Java Microservice Architecture"
    )
    private String content;
    @NotEmpty(message = "Author id can not be null or empty")
    @Schema(
            description = "Author id of the user who has created the post",
            example = "author_id = 1 (representing ADMIN or NORMAL_USER)"
    )
    private String author_id;

}
