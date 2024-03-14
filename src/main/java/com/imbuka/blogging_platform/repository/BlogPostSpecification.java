package com.imbuka.blogging_platform.repository;

import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class BlogPostSpecification {
    public static Specification<BlogPost> searchBlogPost(String title, String content) {
        return (root, query, criteriaBuilder) -> {
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), likePattern(title));
            Predicate contentPredicate = criteriaBuilder.like(root.get("content"), likePattern(content));
            return criteriaBuilder.or(titlePredicate, contentPredicate);

        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
