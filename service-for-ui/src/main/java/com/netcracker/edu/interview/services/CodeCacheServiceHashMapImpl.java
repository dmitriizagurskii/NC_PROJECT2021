package com.netcracker.edu.interview.services;

import com.netcracker.edu.interview.domain.CodeText;
import com.netcracker.edu.interview.interfaces.CodeCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;
@Slf4j
@Service
public class CodeCacheServiceHashMapImpl implements CodeCacheService {

    private HashMap<UUID, CodeText> codeTextMap = new HashMap<>();

    @Override
    public CodeText getCodeTextById(String uid) {
        log.info("codeTextMap: " + codeTextMap);
        UUID uidKey = UUID.fromString(uid);
        if (codeTextMap.containsKey(uidKey)) {
            return codeTextMap.get(uidKey);
        } else {
            CodeText codeText = new CodeText(UUID.fromString(uid));
            codeTextMap.put(codeText.getUid(), codeText);
            return codeText;
        }
    }


}
