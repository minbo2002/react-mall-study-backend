package org.zerock.mallapi.domain.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zerock.mallapi.common.annotation.ReadService;
import org.zerock.mallapi.common.exception.NotFoundException;
import org.zerock.mallapi.domain.todo.dto.TodoRead;
import org.zerock.mallapi.domain.todo.entity.Todo;
import org.zerock.mallapi.infrastructure.todo.TodoRepository;

@ReadService
@RequiredArgsConstructor
@Slf4j
public class TodoReadService {

    private final TodoRepository todoRepository;

    public TodoRead get(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        return TodoRead.from(todo);
    }
}
