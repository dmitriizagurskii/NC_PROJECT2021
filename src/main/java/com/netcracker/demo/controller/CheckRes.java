package com.netcracker.demo.controller;

import com.netcracker.demo.model.Policy;
import com.netcracker.demo.model.ReqPolicy;
import com.netcracker.demo.model.RespPolicy;
import com.netcracker.demo.model.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/policy")
public class CheckRes {
    @Autowired
    private PolicyRepository policyRepository;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespPolicy checkResource(@RequestBody ReqPolicy reqPolicy, SecurityContextHolderAwareRequestWrapper request) {
        List<Policy> policyList = policyRepository.findPoliciesByResourceAndAlgorithm(reqPolicy.getResource(), reqPolicy.getAlgorithm());
        for (Policy value:
             policyList) {
            if (request.isUserInRole(value.getRole_name())) {
                return new RespPolicy("true");
            }
        }
        return new RespPolicy("false");
    }
}
