package com.sanluis.spring.MartaMorenoBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanluis.spring.MartaMorenoBlog.repository.PostRepository;
import com.sanluis.spring.MartaMorenoBlog.vo.Post;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(Integer id) {
		return postRepository.getOne(id);
	}
   // Post findById(Long id);
	public Post savePost(Post post) {
		return postRepository.save(post);
	}
    
	public void updatePost(Post post) {
		Post postAnterior = postRepository.getOne(post.getId());
		postAnterior.setAutor(post.getAutor());
		postAnterior.setTitulo(post.getTitulo());
		postAnterior.setContenido(post.getContenido());
		postRepository.flush();
	}
	
	public void deletePost(Integer id) {
		postRepository.deleteById(id);
	}

}
