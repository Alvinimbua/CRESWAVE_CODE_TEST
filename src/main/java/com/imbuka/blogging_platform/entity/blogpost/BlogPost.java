package com.imbuka.blogging_platform.entity.blogpost;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.imbuka.blogging_platform.entity.comment.Comment;
import com.imbuka.blogging_platform.entity.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private User author;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private List<Comment> comment;

}
