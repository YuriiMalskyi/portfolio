package com.malskyi.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malskyi.project.entity.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
		
}
