package com.sparta.crawling.repository;

import com.sparta.crawling.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch,Long> {
}
