package org.zerock.mallapi.domain.todo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zerock.mallapi.common.BaseTimeEntity;

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

    private Boolean complete;

    private LocalDateTime dueDate;

    @Builder
    private Todo(Long id, String title, String content, Boolean complete, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.dueDate = dueDate;
    }
}
