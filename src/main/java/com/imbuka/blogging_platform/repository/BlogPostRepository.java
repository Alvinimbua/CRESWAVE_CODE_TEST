package com.imbuka.blogging_platform.repository;

import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>, JpaSpecificationExecutor<BlogPost> {

    Page<BlogPost> findAll(Specification<BlogPost> specification, Pageable pageable);
}
