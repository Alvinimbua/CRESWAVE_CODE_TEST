package com.imbuka.blogging_platform.controller;

import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.dto.ErrorResponseDto;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.service.BlogPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs from Blog Post in Creswave Blog Platform",
        description = "CRUD REST APIs in Creswave blogs to CREATE,UPDATE,GET AND DELETE blog post details"
)
@RestController
@RequestMapping(path = "/api/v1/blogpost", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('ADMIN', 'NORMAL_USER')")
@Validated
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Operation(
            summary = "Create Blog Post REST API",
            description = "REST API to create new Blog Post in  Creswave Blog platform"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/createBlog")
    @PreAuthorize("hasAnyAuthority('admin:create', 'normal_user:create')")
    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody BlogPostDTO blogPost) {
        BlogPost blogpost = blogPostService.createBlogPost(blogPost);
        log.info("Blog post created");
        return ResponseEntity.status(HttpStatus.CREATED).body(blogpost);
    }

    @Operation(
            summary = "Get Blog Post REST API",
            description = "REST API to get the Blog Posts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/getAllBlogs")
    @PreAuthorize("hasAnyAuthority('admin:read', 'normal_user:read')")
    public ResponseEntity<Page<BlogPost>> getAllBlogPosts(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        Page<BlogPost> blogs = blogPostService.getAllBlogPosts(pageNo, pageSize, sortBy, sortDirection, title, content);
        return ResponseEntity.ok(blogs);
    }

    @Operation(
            summary = "Get Blog Post By Id REST API",
            description = "REST API to get the Blog Posts by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/getBlogById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'normal_user:read')")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        BlogPost blog = blogPostService.getBlogPostById(id);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Update Blog Post REST API",
            description = "REST API to update Blog Post based on Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception  failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/updateBlogById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public ResponseEntity<BlogPost> update(@Valid @PathVariable Long id, @RequestBody BlogPostDTO blogPostDTO) {
        BlogPost updatedBlog = blogPostService.updateBlogPost(id, blogPostDTO);
        if (updatedBlog != null) {
            return ResponseEntity.status(200).body(updatedBlog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(
            summary = "Delete Blog posts REST API",
            description = "REST API to delete Blog posts based on Blog Id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/deleteBlogById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }

}
