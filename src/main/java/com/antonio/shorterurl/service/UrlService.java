package com.antonio.shorterurl.service;

import com.antonio.shorterurl.dto.UrlRequest;
import com.antonio.shorterurl.dto.UrlResponse;
import com.antonio.shorterurl.entity.Url;
import com.antonio.shorterurl.exception.exceptions.UrlExpiredException;
import com.antonio.shorterurl.exception.exceptions.UrlNotFoundException;
import com.antonio.shorterurl.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlService {

    private UrlRepository repository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlResponse shortenUrl(UrlRequest request) {

        Url url;

        do {
            url = new Url(
                    null,
                    request.url(),
                    getApiUrl() + generateShortCode(),
                    LocalDateTime.now().plusMinutes(1)
            );
        } while (repository.findByShortenUrl(url.getShortenUrl()).isPresent());

        repository.save(url);
        return new UrlResponse(url.getLongUrl(), url.getShortenUrl());
    }

    public String getUrl(String shortenCode) {

        Url url = repository
            .findByShortenUrl(getApiUrl() + shortenCode)
            .orElseThrow(() -> new UrlNotFoundException("A url não foi encontrada"));

        if (url.getExpiresAt().isBefore(LocalDateTime.now())) {
            repository.deleteById(url.getId());
            throw new UrlExpiredException("A url foi expirada");
        }

        return url.getLongUrl();
    }

    private String generateShortCode() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 6);
    }

    private String getApiUrl () {
        return httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/";
    }
}
