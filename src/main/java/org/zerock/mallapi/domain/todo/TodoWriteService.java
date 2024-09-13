package org.zerock.mallapi.domain.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.zerock.mallapi.common.annotation.WriteServce;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;
import org.zerock.mallapi.domain.todo.entity.Todo;
import org.zerock.mallapi.infrastructure.todo.TodoRepository;

@WriteServce
@RequiredArgsConstructor
@Slf4j
public class TodoWriteService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public Long register(TodoCreate todoCreate) {
        Todo todo = todoRepository.save(todoCreate.toTodo());

        return todo.getId();
    }
}
