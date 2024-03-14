package com.imbuka.blogging_platform;

import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.repository.BlogPostRepository;
import com.imbuka.blogging_platform.service.BlogPostService;
import com.imbuka.blogging_platform.service.impl.BlogPostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BlogPostServiceTest {
    @InjectMocks
    private BlogPostServiceImpl blogPostService;

    @Mock
    private BlogPostRepository blogPostRepository;

    @Test
    void createBlogPost_ReturnsCreatedBlogPost() {

        BlogPostDTO blogPostDTO = new BlogPostDTO();
        BlogPost createdBlogPost = new BlogPost();
        Mockito.when(blogPostRepository.save(Mockito.any(BlogPost.class))).thenReturn(createdBlogPost);

        BlogPost result = blogPostService.createBlogPost(blogPostDTO);

        assertEquals(createdBlogPost, result);
    }
}
