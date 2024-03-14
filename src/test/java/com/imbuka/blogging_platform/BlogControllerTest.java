package com.imbuka.blogging_platform;

import com.imbuka.blogging_platform.controller.BlogPostController;
import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.service.BlogPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @InjectMocks
    private BlogPostController blogPostController;

    @Mock
    private BlogPostService blogPostService;

    @Test
    void createPost_ReturnsCreatedBlogPost() {
        BlogPostDTO blogPostDTO = new BlogPostDTO();
        BlogPost createdBlogPost = new BlogPost();
        Mockito.when(blogPostService.createBlogPost(blogPostDTO)).thenReturn(createdBlogPost);

        ResponseEntity<BlogPost> response = blogPostController.createPost(blogPostDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdBlogPost, response.getBody());
    }
}
