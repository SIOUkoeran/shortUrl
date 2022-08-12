package com.example.shorturl.idgenerator;


import com.example.shorturl.exception.ErrorCode;
import com.example.shorturl.exception.IllegalTickStateException;
import com.example.shorturl.exception.IllegalTimeStampStateException;
import com.example.shorturl.idgenerator.time.TimeSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class IdGeneratorSnowflake implements IdGenerator{

    private static final long sequenceBits = 13L;
    private static final long sequenceMask = ~(-1L << sequenceBits);
    private static final ReentrantLock lock = new ReentrantLock();
    private static final long timestampBits = 41L;
    private static final long datacenterIdBits = 5L;
    private static final long generatorBits = 5L;
    private static final long  generatorId = 1L;
    private static final long shiftTime = datacenterIdBits + sequenceBits;
    private static final long maskTime = timestampBits;

    private long lastTimeStamp = -1L;
    private long sequence = 0L;

    private final TimeSource timeSource;

    public IdGeneratorSnowflake(TimeSource timeSource) {
        this.timeSource = timeSource;
    }

    @Override
    public long generateRandomId() {
        long timeStamp = timeSource.getTick();
        if (timeStamp < 0){
            throw new IllegalTickStateException(ErrorCode.IllegalTickState);
        }

        try {
            lock.lockInterruptibly();

            if (timeStamp < lastTimeStamp) {
                throw new IllegalTimeStampStateException(ErrorCode.IllegalTimestampState);
            }
            log.info("timestamp {} : lastTimeStamp {}", timeStamp, lastTimeStamp);
            if (timeStamp == lastTimeStamp) {
                sequence = (sequenceBits + 1) & sequenceMask;
            } else {
                sequence = 0L;
            }
            lastTimeStamp = timeStamp;
        } catch (InterruptedException e){
            log.error("interrupted Exception ", e);
        } finally {
           lock.unlock();
        }
        return (timeStamp << shiftTime) | (datacenterIdBits << datacenterIdBits) | (generatorId << generatorBits) | sequence;
    }

}
