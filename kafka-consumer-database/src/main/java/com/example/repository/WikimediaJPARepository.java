package com.example.repository;

import com.example.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaJPARepository extends JpaRepository<WikimediaData, Long> {
}
