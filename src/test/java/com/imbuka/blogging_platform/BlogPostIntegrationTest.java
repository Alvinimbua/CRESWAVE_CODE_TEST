package com.imbuka.blogging_platform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogPostIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPost_ReturnsCreatedBlogPost() throws Exception {
        BlogPostDTO blogPostDTO = new BlogPostDTO();

        MvcResult result = mockMvc.perform(post("/createdBlog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPostDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        BlogPost createdBlogPost = objectMapper.readValue(result.getResponse().getContentAsString(), BlogPost.class);
        assertNotNull(createdBlogPost);
    }
}
