package com.antonio.shorterurl.repository;

import com.antonio.shorterurl.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortenUrl(String shortenUrl);
}
