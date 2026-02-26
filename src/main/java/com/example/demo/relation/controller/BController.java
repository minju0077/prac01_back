package com.example.demo.relation.controller;

import com.example.demo.common.model.BaseResponse;
import com.example.demo.relation.model.BDto;
import com.example.demo.relation.service.BService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/b")
@RestController
@RequiredArgsConstructor
public class BController {
    private final BService bService;

    @PostMapping("/reg/{aIdx}")
    public ResponseEntity reg(@PathVariable Long aIdx, @RequestBody BDto.BReq dto){
        BDto.BRes result = bService.reg(aIdx, dto);
        return ResponseEntity.ok(BaseResponse.success(result));
    }

}
