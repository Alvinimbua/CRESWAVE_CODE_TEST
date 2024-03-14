package com.imbuka.blogging_platform.controller;

import com.imbuka.blogging_platform.dto.CommentDTO;
import com.imbuka.blogging_platform.dto.ErrorResponseDto;
import com.imbuka.blogging_platform.entity.comment.Comment;
import com.imbuka.blogging_platform.service.CommentService;
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

import java.util.List;

@Tag(
        name = "CRUD REST APIs from Comments in Creswave Blog Platform",
        description = "CRUD REST APIs in Creswave blogs to CREATE,UPDATE,GET AND DELETE comments details"
)
@RestController
@RequestMapping(path = "/api/v1/comment", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('ADMIN', 'NORMAL_USER')")
@Validated
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Create Comments REST API",
            description = "REST API to create new Comments in  Creswave Blog Posts"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping("/createComment")
    @PreAuthorize("hasAnyAuthority('admin:create', 'normal_user:create')")
    public ResponseEntity<Comment> createComment( @Valid @RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.createComment(commentDTO);
        log.info("Comment created Successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @Operation(
            summary = "Get Comments REST API",
            description = "REST API to get all comments on blogs"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/getAllComments")
    @PreAuthorize("hasAnyAuthority('admin:read', 'normal_user:read')")
    public ResponseEntity<Page<Comment>> getAllComments(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        Page<Comment> comments = commentService.getAllComments(pageNo, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(comments);
    }

    @Operation(
            summary = "Get comments By Id REST API",
            description = "REST API to get the Comments by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/getCommentById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'normal_user:read')")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentsById(id);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Update comments REST API",
            description = "REST API to update Comments based on Id"
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
    @PutMapping("/updateCommentById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public ResponseEntity<Comment> updateCommentById(@Valid @PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        Comment updatedComment = commentService.updateComment(id, commentDTO);
        if (updatedComment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Delete Comments REST API",
            description = "REST API to delete Comments based on Comment Id"
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
    @DeleteMapping("/deleteCommentById/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public ResponseEntity<Void> deleteBlogById(@PathVariable Long id) {
        commentService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }
}
