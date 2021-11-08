package com.netcracker.project21.uiservice.сontrollers;

import com.netcracker.project21.uiservice.domain.CodeChange;
import com.netcracker.project21.uiservice.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

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
        System.out.println(codeChange);
        codeService.updatePiece(Integer.valueOf(id), codeChange);
        return codeChange;
    }


}
