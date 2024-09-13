package org.zerock.mallapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;
import org.zerock.mallapi.domain.todo.dto.TodoRead;
import org.zerock.mallapi.domain.todo.dto.TodoUpdate;

import java.time.LocalDate;

public class TodoDto {

    @Builder
    public record Create(
        String title,
        String content,
        String writer,
        Boolean complete,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate
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

    @Builder
    public record Info(
        Long id,
        String title,
        String content,
        String writer,
        Boolean complete,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate
    ){
        public static Info from(TodoRead todoRead) {
            return Info.builder()
                .id(todoRead.id())
                .title(todoRead.title())
                .content(todoRead.content())
                .writer(todoRead.writer())
                .complete(todoRead.complete())
                .dueDate(todoRead.dueDate())
                .build();
        }
    }

    @Builder
    public record Update(
        String title,
        String content,
        Boolean complete,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dueDate
    ) {
        public TodoUpdate toTodoUpdate() {
            return TodoUpdate.builder()
                .title(this.title())
                .content(this.content())
                .complete(this.complete())
                .dueDate(this.dueDate())
                .build();
        }
    }
}
