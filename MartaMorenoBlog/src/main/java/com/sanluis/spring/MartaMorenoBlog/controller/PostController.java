package com.sanluis.spring.MartaMorenoBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanluis.spring.MartaMorenoBlog.service.PostService;
import com.sanluis.spring.MartaMorenoBlog.vo.Post;

@Controller
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@GetMapping
	public String index(Model model) {
	List<Post> findAllPosts = postService.findAll();
	model.addAttribute("findAllPosts", findAllPosts);

	return "index";
	}
}