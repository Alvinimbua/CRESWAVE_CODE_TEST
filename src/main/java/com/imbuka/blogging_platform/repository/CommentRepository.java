package com.imbuka.blogging_platform.repository;

import com.imbuka.blogging_platform.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
