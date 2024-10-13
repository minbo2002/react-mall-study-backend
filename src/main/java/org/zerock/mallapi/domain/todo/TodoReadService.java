package org.zerock.mallapi.domain.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.common.annotation.ReadService;
import org.zerock.mallapi.common.exception.NotFoundException;
import org.zerock.mallapi.domain.PageRequestDTO;
import org.zerock.mallapi.domain.PageResponseDTO;
import org.zerock.mallapi.domain.todo.dto.TodoRead;
import org.zerock.mallapi.domain.todo.entity.Todo;
import org.zerock.mallapi.infrastructure.todo.TodoRepository;
import org.zerock.mallapi.presentation.dto.TodoDto;

import java.util.List;

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

    public PageResponseDTO<TodoDto.Info> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("id").ascending()
        );

        Page<Todo> todos = todoRepository.findAll(pageable);

        List<TodoDto.Info> todoReads = todos.stream()
                .map(TodoRead::from)
                .map(TodoDto.Info::from)
                .toList();

        long totalCount = todos.getTotalElements();

        return new PageResponseDTO<>(todoReads, pageRequestDTO, totalCount);
    }
}
