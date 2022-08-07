package com.example.shorturl.idgenerator.time;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public interface TimeSource {
    long getTick();
    Duration getTickDuration();
    Instant getEpoch();
}
