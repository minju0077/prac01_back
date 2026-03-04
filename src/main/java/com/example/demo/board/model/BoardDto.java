package com.example.demo.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

public class BoardDto {

    @Getter
    @Builder
    public static class PageRes{
        private List<ListRes> boardList;
        private int totalPage;
        private long totalCount;
        private int currentPage;
        private int currentSize;
    }
    @Getter
    public static class RegReq {
        @Schema(description = "제목, 제목은 50글자까지만 가능합니다.", required = true, example = "제목01")
        private String title;
        private String content;

        public Board toEntity() {
            return Board.builder()
                    .title(this.title)
                    .content(this.content)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class RegRes {
        private Long idx;
        private String title;
        private String content;
        private String username;

        public static RegRes from(Board entity) {
            return RegRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .username(entity.getUser().getName())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String title;
        private String writer;
        private int replyCount;
        private int likeslist;

        public static ListRes from(Board entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .writer(entity.getUser().getName())
                    .likeslist(entity.getLikesList().size())
                    .replyCount(entity.getReply().size())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String content;
        private String writer;
        private int likeslist;

        public static ReadRes from(Board entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .writer(entity.getUser().getName())
                    .likeslist(entity.getLikesList().size())
                    .build();
        }
    }
}
