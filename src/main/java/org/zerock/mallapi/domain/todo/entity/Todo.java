package org.zerock.mallapi.domain.todo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zerock.mallapi.domain.BaseTimeEntity;
import org.zerock.mallapi.domain.todo.dto.TodoUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "todos")
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    private String writer;

    private Boolean complete;

    private LocalDate dueDate;

    @Builder
    private Todo(
            Long id,
            String title,
            String content,
            String writer,
            Boolean complete,
            LocalDate dueDate
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.complete = complete;
        this.dueDate = dueDate;
    }

    public void update(TodoUpdate todoUpdate) {
        this.title = todoUpdate.title();
        this.content = todoUpdate.content();
        this.complete = todoUpdate.complete();
        this.dueDate = todoUpdate.dueDate();
    }
}
