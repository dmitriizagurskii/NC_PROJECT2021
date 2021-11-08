package com.netcracker.project21.uiservice.сontrollers;


import com.netcracker.project21.uiservice.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollaborativeCodeRestController {

    private CodeService codeService;

    @Autowired
    public void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }

//    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowedHeaders = "*")
    @GetMapping("/interview-room/{id}/code")
    public String getCode(@PathVariable Integer id) {
        return codeService.getText(id);
    }


}
