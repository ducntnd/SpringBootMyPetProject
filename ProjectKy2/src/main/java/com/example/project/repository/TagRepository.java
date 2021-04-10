package com.example.project.repository;

import com.example.project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag,Long> {

}
