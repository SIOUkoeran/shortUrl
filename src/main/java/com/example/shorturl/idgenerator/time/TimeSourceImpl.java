package com.example.shorturl.idgenerator.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Slf4j
@Service
public class TimeSourceImpl implements TimeSource{
    private static final Instant epoch = Instant.ofEpochMilli(1577836800000L);
    private final long start;
    private final long offset;

    public TimeSourceImpl() {
        offset = Instant.now().toEpochMilli() - epoch.toEpochMilli();
        start = System.nanoTime() / 1_000_000;
    }

    @Override
    public long getTick() {
        return System.currentTimeMillis() - epoch.toEpochMilli();
    }

    @Override
    public Duration getTickDuration() {
        return Duration.ofMillis(1);
    }

    @Override
    public Instant getEpoch() {
        return epoch;
    }

    private long elapsed(){
        return (System.nanoTime() / 1_000_000) - start;
    }

    @Override
    public String toString() {
        return "TimeSourceImpl{" +
                "start=" + start +
                ", offset=" + offset +
                ", epoch=" + epoch +
                '}';
    }
}
