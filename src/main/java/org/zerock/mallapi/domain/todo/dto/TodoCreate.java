package org.zerock.mallapi.domain.todo.dto;

import lombok.Builder;
import org.zerock.mallapi.domain.todo.entity.Todo;

import java.time.LocalDate;

@Builder
public record TodoCreate(
        String title,
        String content,
        String writer,
        Boolean complete,
        LocalDate dueDate
) {
    public Todo toTodo() {
        return Todo.builder()
            .title(title)
            .content(content)
            .writer(writer)
            .complete(complete)
            .dueDate(dueDate)
            .build();
    }
}
