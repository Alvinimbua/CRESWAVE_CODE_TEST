package com.imbuka.blogging_platform.entity.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonBackReference
    private BlogPost blog;

}
