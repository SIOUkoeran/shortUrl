package com.example.shorturl.idgenerator;

import com.example.shorturl.idgenerator.time.TimeSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class IdGeneratorSnowflakeTest {

    @Autowired
    IdGenerator idGenerator;

    @Test
    @DisplayName("snowflake 알고리즘 사용 ID 생성")
    void generateIdUsingSnowflakeAlgorithm(){
        long l = idGenerator.generateRandomId();
        log.info("random Id {}", l);
    }
}