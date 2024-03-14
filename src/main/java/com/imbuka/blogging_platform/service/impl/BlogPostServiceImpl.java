package com.imbuka.blogging_platform.service.impl;

import com.imbuka.blogging_platform.dto.BlogPostDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.entity.users.User;
import com.imbuka.blogging_platform.repository.BlogPostRepository;
import com.imbuka.blogging_platform.repository.BlogPostSpecification;
import com.imbuka.blogging_platform.repository.UserRepository;
import com.imbuka.blogging_platform.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;

    @Override
    public Page<BlogPost> getAllBlogPosts(int pageNo, int pageSize, String sortBy, String sortDirection, String title, String content) {
        final Specification<BlogPost> specification =
                BlogPostSpecification.searchBlogPost(title, content);
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return blogPostRepository.findAll(specification, pageable);
    }

    @Override
    public BlogPost getBlogPostById(Long blog_id) {
        return blogPostRepository.findById(blog_id)
                .orElseThrow(() -> new RuntimeException("Blog post not found with id " + blog_id));
    }

    @Override
    public BlogPost createBlogPost(BlogPostDTO blogPost) {
        User author = userRepository.findById(Long.valueOf(blogPost.getAuthor_id()))
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id " + blogPost.getAuthor_id()));
//        if (blogPost.getTitle() == null || blogPost.getTitle().isEmpty()) {
//            throw new IllegalArgumentException("Title cannot be empty");
//        }
//
//        if (blogPost.getContent() == null || blogPost.getContent().isEmpty()) {
//            throw new IllegalArgumentException("Content cannot be empty");
//        }
        BlogPost newBlog = new BlogPost();
        newBlog.setContent(blogPost.getContent());
        newBlog.setTitle(blogPost.getTitle());
        newBlog.setAuthor(author);
        return blogPostRepository.save(newBlog);
    }

    @Override
    public BlogPost updateBlogPost(Long blog_id, BlogPostDTO blogPostDTO) {
        BlogPost blogPost = getBlogPostById(blog_id);
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPost.getContent());

        return blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteBlogPost(Long blog_id) {
        BlogPost blogPost = getBlogPostById(blog_id);
        blogPostRepository.delete(blogPost);

    }
}
