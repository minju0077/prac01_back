package com.example.demo.relation.controller;

import com.example.demo.common.model.BaseResponse;
import com.example.demo.relation.service.BService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/b")
@RestController
@RequiredArgsConstructor
public class BController {
    private final BService bService;

    @PostMapping("/reg/{aIdx}")
    public ResponseEntity reg(@PathVariable Long aIdx){
        bService.reg(aIdx);
        return ResponseEntity.ok(BaseResponse.success());
    }

}
