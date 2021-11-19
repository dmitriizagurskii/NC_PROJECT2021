package com.netcracker.edu.interview.interfaces;

import com.netcracker.edu.interview.domain.CodeChange;
import com.netcracker.edu.interview.domain.CodeText;

public interface CodeService {
    // Applying change to code by room id and send full code text to task management service,
    // if counter of changes == 10
    // TODO: 08.11.2021 make rest request to task management service to save text
    void changeCodeText(String id, CodeChange codeChange);

    // returns full text by room id
    // if there's no such room with specified id - creates it
    String getCodeText(String id);


    void checkAndSendCodeText(CodeText codeText);

}
