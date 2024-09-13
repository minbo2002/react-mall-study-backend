package org.zerock.mallapi.infrastructure.todo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.domain.todo.TodoWriteService;
import org.zerock.mallapi.domain.todo.dto.TodoUpdate;
import org.zerock.mallapi.domain.todo.entity.Todo;
import org.zerock.mallapi.presentation.dto.TodoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoWriteService todoWriteService;

    @Test
    @DisplayName("Todo 생성")
    public void insertTodo() {

        for(int i=0; i<100; i++) {
            Todo todo = Todo.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .writer("writer..."+i)
                    .complete(false)
                    .dueDate(LocalDate.now())
                    .build();

            todoRepository.save(todo);

            assertThat(todo.getId()).isNotNull();
        }
    }

    @Test
    @DisplayName("Todo 조회")
    public void selectTodo() {

        Long id = 1L;

        Todo todo = todoRepository.findById(id)
                .orElseThrow();

        assertThat(todo.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Todo 수정")
    public void updateTodo() {

        Long id = 1L;

        Todo todo = todoRepository.findById(id)
                .orElseThrow();

        TodoUpdate todoUpdate = TodoUpdate.builder()
                .title("title update...")
                .content("content update...")
                .complete(false)
                .dueDate(LocalDate.now())
                .build();

        todo.update(todoUpdate);

        todoRepository.save(todo);

        assertThat(todo.getTitle()).isEqualTo("title update...");
    }

    @Test
    @DisplayName("Todo 삭제")
    public void deleteTodo() {

        Long id = 100L;

        todoRepository.deleteById(id);

        assertThat(todoRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("Todo 페이징")
    public void testPaging() {

        // 페이지번호는 0부터
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info("total pages: {}", result.getTotalPages());
        log.info("total elements: {}", result.getTotalElements());
        log.info("content: {}", result.getContent());
    }

    @Test
    @DisplayName("Todo DTO 클래스를 이용한 TODO 생성")
    public void testRegisterTodo() {
        TodoDto.Create create = TodoDto.Create.builder()
            .title("title")
            .content("content")
            .writer("writer")
            .complete(false)
            .dueDate(LocalDateTime.now())
            .build();

        Long todoId = todoWriteService.register(create.toTodoCreate());

        log.info("todoId: {}", todoId);
        assertThat(todoId).isNotNull();
    }
}
