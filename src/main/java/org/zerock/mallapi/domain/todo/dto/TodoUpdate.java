package org.zerock.mallapi.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TodoUpdate {

    private String title;
    private String content;
    private String writer;
    private Boolean complete;
    private LocalDateTime dueDate;

    @Builder
    private TodoUpdate(
        String title,
        String content,
        String writer,
        Boolean complete,
        LocalDateTime dueDate
    ) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.complete = complete;
        this.dueDate = dueDate;
    }
}
