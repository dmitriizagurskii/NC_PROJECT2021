package com.netcracker.project21.uiservice.services;

import com.netcracker.project21.uiservice.domain.CodeChange;
import com.netcracker.project21.uiservice.domain.CodeText;
import com.netcracker.project21.uiservice.interfaces.CodeCacheService;
import com.netcracker.project21.uiservice.interfaces.CodeService;
import com.netcracker.project21.uiservice.services.utils.CodeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableScheduling
@Service
public class CodeServiceImpl implements CodeService {
    private final int MAX_CHANGES = 10;
    private CodeCacheService codeCacheService;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private ScheduledFuture<?> scheduledFuture;


    @Autowired
    private void setScheduledThreadPoolExecutor(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
    }

    @Autowired
    private void setCodeCacheService(CodeCacheService codeCacheService) {
        this.codeCacheService = codeCacheService;
    }


    @Override
    public void changeCodeText(String id, CodeChange codeChange) {
        log.info("CodeText id: " + id);
        CodeText codeText = codeCacheService.getCodeTextById(id);
        log.info("CodeText retrieved by id: " + codeText);
        CodeUtility.applyChange(codeText.getStringBuilderOfCodeText(), codeChange.getCodeChangeInfo());

        checkAndSendCodeText(codeText);
    }

    @Override
    public void checkAndSendCodeText(CodeText codeText) {
        if (scheduledFuture != null)
            scheduledFuture.cancel(false);

        if (codeText.incrementAndGetCounterValue() >= MAX_CHANGES) {
            sendCodeText(codeText).run();
        }
        scheduledFuture = scheduleSending(codeText);
    }

    private ScheduledFuture<?> scheduleSending(CodeText codeText) {
       return scheduledThreadPoolExecutor.schedule(sendCodeText(codeText), 5, TimeUnit.SECONDS);
    }


    // TODO: 08.11.2021 make rest request to task management service to save text
    private Runnable sendCodeText(CodeText codeText) {
        return () -> {
            log.info("Sending text to task management service: " + codeText.getTextString());
            codeText.setCounterValue(0);
        };
    }

    @Override
    public String getCodeText(String id) {
       return codeCacheService.getCodeTextById(id).getTextString();
    }



}
