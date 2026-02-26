package com.example.demo.reply.model;

import lombok.Builder;
import lombok.Getter;

public class ReplyDto {
    @Builder
    @Getter
    public static class ReplyRes{
        private Long idx;
        private String content;
        private String writer;

        public static ReplyDto.ReplyRes from(Reply entity){
            return ReplyRes.builder()
                    .idx(entity.getIdx())
                    .content(entity.getContent())

                    .build();
        }
    }
}
