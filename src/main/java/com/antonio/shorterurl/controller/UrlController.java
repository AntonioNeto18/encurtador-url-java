package com.antonio.shorterurl.controller;

import com.antonio.shorterurl.dto.UrlRequest;
import com.antonio.shorterurl.dto.UrlResponse;
import com.antonio.shorterurl.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UrlController {

    private UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten_url")
    public ResponseEntity<UrlResponse> shortenUrl (@RequestBody UrlRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.shortenUrl(request));
    }

    @GetMapping("/{shorten_code}")
    public ResponseEntity<Void> getUrl (@PathVariable String shorten_code) {
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, service.getUrl(shorten_code)).build();
    }
}
