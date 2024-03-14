package com.imbuka.blogging_platform;

import com.imbuka.blogging_platform.entity.blogpost.BlogPost;
import com.imbuka.blogging_platform.repository.BlogPostRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class BloggingPlatformApplicationTests {

	@Autowired
	private BlogPostRepository blogPostRepository;

	@Test
	public void testCreateBlogPost() {
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle("Kotlin Structure");
		blogPost.setContent("How to spin up kotlin");

		blogPostRepository.save(blogPost);
	}

	@Test
	public void testFindBlogPostById(Long id) {
		BlogPost blogPost = blogPostRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("Student with id not found" + id));
		System.out.println(blogPost);
	}

	@Test
	public void testUpdateStudent(Long id) {
		BlogPost blogPost = blogPostRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("Student with id not found" + id));
		blogPost.setTitle("Go lang Architecture");

		blogPostRepository.save(blogPost);
	}
}
