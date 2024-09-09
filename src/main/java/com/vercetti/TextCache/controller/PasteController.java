package com.vercetti.TextCache.controller;

import model.Paste;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/pastes")
public class PasteController {

    private final RedisTemplate<String, Paste> redisTemplate;

    private static final long TTL_SECONDS = 900;

    private static final String PASTE_KEY_PREFIX = "paste:";

    private final Set<String> usedIds = new HashSet<>();

    public PasteController(RedisTemplate<String, Paste> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/{hash}")
    @ResponseStatus(HttpStatus.OK)
    public Paste getPaste(@PathVariable String hash) {
        return redisTemplate.opsForValue().get(PASTE_KEY_PREFIX + hash);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String savePaste(@RequestBody Paste paste) {
        String hash = generateUnique();
        paste.setHash(hash);
        redisTemplate.opsForValue().set(PASTE_KEY_PREFIX + hash, paste, TTL_SECONDS, TimeUnit.SECONDS);
        return hash;
    }

    // generates a unique 5-letter hash
    private String generateUnique() {
        String uniqueId;
        do {
            uniqueId = RandomStringUtils.randomAlphanumeric(6);
        } while (usedIds.contains(uniqueId));

        usedIds.add(uniqueId);
        return uniqueId;
    }
}
