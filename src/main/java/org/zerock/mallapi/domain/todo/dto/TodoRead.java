package org.zerock.mallapi.domain.todo.dto;

import lombok.Builder;
import org.zerock.mallapi.domain.todo.entity.Todo;

import java.time.LocalDate;

@Builder
public record TodoRead(
        Long id,
        String title,
        String content,
        String writer,
        Boolean complete,
        LocalDate dueDate
) {
    public static TodoRead from(Todo todo) {
        return TodoRead.builder()
            .id(todo.getId())
            .title(todo.getTitle())
            .content(todo.getContent())
            .writer(todo.getWriter())
            .complete(todo.getComplete())
            .dueDate(todo.getDueDate())
            .build();
    }
}
