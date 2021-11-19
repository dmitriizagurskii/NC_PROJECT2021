package com.netcracker.project21.uiservice.domain;


import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeText {
    private static int SB_START_LENGTH = 500;
    private UUID uid;
    private StringBuilder text;
    private AtomicInteger counter;

    public CodeText(StringBuilder codeText, AtomicInteger counter) {
        this.uid = UUID.randomUUID();
        this.text = codeText;
        this.counter = counter;
    }

    public CodeText() {
        this.uid = UUID.randomUUID();
        this.text = new StringBuilder(SB_START_LENGTH);
        this.counter = new AtomicInteger(0);
    }

    public CodeText(UUID uuid) {
        this.uid = uuid;
        this.text = new StringBuilder(SB_START_LENGTH);
        this.counter = new AtomicInteger(0);
    }

    public StringBuilder getStringBuilderOfCodeText() {
        return text;
    }

    public void setCodeText(StringBuilder codeText) {
        this.text = codeText;
    }

    public String getTextString() {
        return text.toString();
    }

    public UUID getUid() {
        return uid;
    }

    public int getCounterValue() {
        return this.counter.get();
    }

    public void setCounterValue(int counter) {
        this.counter.set(counter);
    }

    public int incrementAndGetCounterValue() {
        return counter.incrementAndGet();
    }
}


