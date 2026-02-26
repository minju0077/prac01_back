package com.example.demo.relation.model;

import lombok.Builder;
import lombok.Getter;

public class BDto {
    @Getter
    public static class BReq{
        private String b01;
        private String b02;
//        이렇게 DTO 안 받아도 됨
//        private Long aIdx;

        public B toEntity(Long aIdx){
            return B.builder()
                    .b01(this.getB01())
                    .b02(this.getB02())
                    .a(A.builder()
                            .idx(aIdx)
                            .a01()
                    )
                    .build();
        }
    }

    @Builder
    @Getter
    public static class BRes {
        private Long idx;
        private String b01;
        private String b02;

        public static BDto.BRes from(B entity) {
            return BDto.BRes.builder()
                    .idx(entity.getIdx())
                    .b01(entity.getB01())
                    .b02(entity.getB02())
                    .build();
        }
    }
}