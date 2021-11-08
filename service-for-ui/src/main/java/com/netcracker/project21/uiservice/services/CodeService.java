package com.netcracker.project21.uiservice.services;

import com.netcracker.project21.uiservice.domain.CodeChange;
import com.netcracker.project21.uiservice.services.utils.CodeUtility;
import com.netcracker.project21.uiservice.services.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CodeService {
    // Map of interview-room-id -> Pair(interview-room-code, counter-of-changes)
    private Map<Integer, Pair<StringBuilder, AtomicInteger>> codes = new HashMap();
    private CodeUtility codeUtility;

    @Autowired
    private void setCodeUtility(CodeUtility codeUtility) {
        this.codeUtility = codeUtility;
    }

    // Applying change to code by room id and send full code text to task management service,
    // if counter of changes == 10
    // TODO: 08.11.2021 make rest request to task management service to save text
    public void updatePiece(int id, CodeChange codeChange) {
        Pair<StringBuilder, AtomicInteger> code = codes.get(id);
        codeUtility.applyChange(code.getFirst(), codeChange.getCodeChangeInfo());
        AtomicInteger counter = code.getSecond();

        // if 10 changes have occurred, full text is sent to the task management service
        if (counter.incrementAndGet() == 10) {
            System.out.println("Sending text to task management service: " + code.getFirst().toString());
            counter.set(0);
        }
    }

    // returns full text by room id
    // if there's no such room with specified id - creates it
    public String getText(int id) {
        if (codes.containsKey(id)) {
            return codes.get(id).getFirst().toString();
        } else {
            codes.put(id, Pair.of(new StringBuilder(), new AtomicInteger(0)));
            return "";
        }
    }


}
