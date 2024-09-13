package org.zerock.mallapi.domain.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zerock.mallapi.common.annotation.WriteServce;
import org.zerock.mallapi.common.exception.NotFoundException;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;
import org.zerock.mallapi.domain.todo.dto.TodoUpdate;
import org.zerock.mallapi.domain.todo.entity.Todo;
import org.zerock.mallapi.infrastructure.todo.TodoRepository;
import org.zerock.mallapi.presentation.dto.TodoDto;

@WriteServce
@RequiredArgsConstructor
@Slf4j
public class TodoWriteService {

    private final TodoRepository todoRepository;

    public Long register(TodoCreate todoCreate) {
        Todo todo = todoRepository.save(todoCreate.toTodo());

        return todo.getId();
    }

    public void update(Long id, TodoDto.Update update) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        TodoUpdate todoUpdate = update.toTodoUpdate();

        todo.update(todoUpdate);
    }

    public void delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        todoRepository.delete(todo);
    }
}
