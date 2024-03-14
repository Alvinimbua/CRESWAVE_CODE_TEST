package com.imbuka.blogging_platform.service;


import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import org.springframework.data.domain.Page;

public interface BlogPostService {

    Page<BlogPost> getAllBlogPosts(int pageNo, int pageSize, String sortBy, String sortDirection, String title, String content);

    BlogPost getBlogPostById(Long blog_id);

    BlogPost createBlogPost(BlogPostDTO blogPost);

    BlogPost updateBlogPost(Long blog_id, BlogPostDTO blogPostDTO);

    void deleteBlogPost(Long blog_id);

}
