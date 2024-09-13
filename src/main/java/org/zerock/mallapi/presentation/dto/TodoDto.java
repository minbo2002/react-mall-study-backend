package org.zerock.mallapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;

import java.time.LocalDateTime;

public class TodoDto {

    @Builder
    public record Create(
        String title,
        String content,
        String writer,
        Boolean complete,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime dueDate
    ) {
        public TodoCreate toTodoCreate() {
            return TodoCreate.builder()
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .complete(this.complete)
                .dueDate(this.dueDate)
                .build();
        }
    }
}
