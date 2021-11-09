package com.example.shorturl.repository;


import com.example.shorturl.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByUrl(String url);
    Optional<Url> findByShortUrl(String url);
    boolean existsByUrl(String url);
    boolean existsByShortUrl(String url);
}
