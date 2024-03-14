package com.imbuka.blogging_platform.service.impl;

import com.imbuka.blogging_platform.dto.CommentDTO;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.entity.comment.Comment;
import com.imbuka.blogging_platform.repository.BlogPostRepository;
import com.imbuka.blogging_platform.repository.CommentRepository;
import com.imbuka.blogging_platform.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BlogPostRepository blogPostRepository;

    @Override
    public Page<Comment> getAllComments(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getCommentsById(Long comment_id) {
        return commentRepository.findById(comment_id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id " + comment_id));
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        BlogPost blog = blogPostRepository.findById(Long.valueOf(commentDTO.getBlog_id()))
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + commentDTO.getBlog_id()));
        Comment comments = new Comment();
        comments.setBlog(blog);
        comments.setCommentContent(commentDTO.getCommentContent());
        return commentRepository.save(comments);
    }

    @Override
    public Comment updateComment(Long comment_id, CommentDTO commentDTO) {
        Comment comment = getCommentsById(comment_id);
        comment.setCommentContent(commentDTO.getCommentContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteBlogPost(Long comment_id) {
        Comment comment = getCommentsById(comment_id);
        commentRepository.delete(comment);
    }
}
