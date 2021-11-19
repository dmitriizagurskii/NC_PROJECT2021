package com.netcracker.edu.interview.сontrollers;

import com.netcracker.edu.interview.domain.CodeChange;
import com.netcracker.edu.interview.interfaces.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CollaborativeCodingWSController {

    private CodeService codeService;

    @Autowired
    private void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }

    @MessageMapping("/{id}")
    @SendTo("/interview-room/{id}")
    public CodeChange processCodePiece(CodeChange codeChange, @DestinationVariable String id) {
        log.info(String.valueOf(codeChange));
        codeService.changeCodeText(id, codeChange);
        return codeChange;
    }


}
