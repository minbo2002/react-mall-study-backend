package org.zerock.mallapi.presentation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.mallapi.domain.todo.TodoWriteService;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;
import org.zerock.mallapi.presentation.ApiResponse;
import org.zerock.mallapi.presentation.dto.TodoDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoWriteService todoWriteService;

    public ResponseEntity<ApiResponse<Long>> register(
            @RequestBody TodoDto.Create create
    ) {
        TodoCreate todoCreate = create.toTodoCreate();
        Long todoId = todoWriteService.register(todoCreate);

        ApiResponse<Long> response = ApiResponse.ok(todoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
