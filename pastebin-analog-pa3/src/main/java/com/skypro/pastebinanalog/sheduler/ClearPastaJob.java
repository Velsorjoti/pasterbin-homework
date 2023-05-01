package com.skypro.pastebinanalog.sheduler;

import com.skypro.pastebinanalog.repository.PastaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Component
public class ClearPastaJob {
    private final PastaRepository pastaRepository;

    public ClearPastaJob(PastaRepository pastaRepository) {
        this.pastaRepository = pastaRepository;
    }

    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void clearTokens() {
        pastaRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }
}


