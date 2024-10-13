package org.zerock.mallapi.presentation.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mallapi.domain.PageRequestDTO;
import org.zerock.mallapi.domain.PageResponseDTO;
import org.zerock.mallapi.domain.todo.TodoReadService;
import org.zerock.mallapi.domain.todo.TodoWriteService;
import org.zerock.mallapi.domain.todo.dto.TodoCreate;
import org.zerock.mallapi.domain.todo.dto.TodoRead;
import org.zerock.mallapi.presentation.ApiResponse;
import org.zerock.mallapi.presentation.dto.TodoDto;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoRestController {

    private final TodoWriteService todoWriteService;
    private final TodoReadService todoReadService;

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Long>>> register(
            @RequestBody TodoDto.Create create
    ) {
        TodoCreate todoCreate = create.toTodoCreate();
        Long todoId = todoWriteService.register(todoCreate);

        ApiResponse<Map<String, Long>> response = ApiResponse.ok(Map.of("id", todoId));  // Modal 창에 todoId 값을 전달하기위한 key 값으로 id 사용
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoDto.Info>> get(
            @PathVariable(name = "id") Long id
    ) {
        TodoRead todoRead = todoReadService.get(id);
        TodoDto.Info todoInfo = TodoDto.Info.from(todoRead);

        ApiResponse<TodoDto.Info> response = ApiResponse.ok(todoInfo);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageResponseDTO<TodoDto.Info>>> list(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<TodoDto.Info> todoInfos = todoReadService.list(pageRequestDTO);

        ApiResponse<PageResponseDTO<TodoDto.Info>> response = ApiResponse.ok(todoInfos);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> update(
            @PathVariable(name = "id") Long id,
            @RequestBody TodoDto.Update update
    ) {
        todoWriteService.update(id, update);

        ApiResponse<String> response = ApiResponse.ok("Todo update success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(
            @PathVariable(name = "id") Long id
    ) {
        todoWriteService.delete(id);

        ApiResponse<String> response = ApiResponse.ok("Todo delete success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
