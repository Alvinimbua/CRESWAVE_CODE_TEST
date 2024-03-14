package com.imbuka.blogging_platform.service;

import com.imbuka.blogging_platform.dto.CommentDTO;
import com.imbuka.blogging_platform.entity.comment.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<Comment> getAllComments(int pageNo, int pageSize, String sortBy, String sortDirection);

    Comment getCommentsById(Long comment_id);

    Comment createComment(CommentDTO commentDTO);

    Comment updateComment(Long comment_id, CommentDTO commentDTO);

    void deleteBlogPost(Long comment_id);
}
