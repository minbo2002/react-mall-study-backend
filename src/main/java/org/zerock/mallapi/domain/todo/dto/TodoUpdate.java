package org.zerock.mallapi.domain.todo.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TodoUpdate(
    String title,
    String content,
    Boolean complete,
    LocalDate dueDate
) {
}
