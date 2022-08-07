package com.example.shorturl.idgenerator;

import org.springframework.stereotype.Service;

@Service
public interface IdGenerator {

    public long generateRandomId();
}
