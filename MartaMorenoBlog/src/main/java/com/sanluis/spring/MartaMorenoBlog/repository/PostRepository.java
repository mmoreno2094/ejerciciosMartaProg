package com.sanluis.spring.MartaMorenoBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanluis.spring.MartaMorenoBlog.vo.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
