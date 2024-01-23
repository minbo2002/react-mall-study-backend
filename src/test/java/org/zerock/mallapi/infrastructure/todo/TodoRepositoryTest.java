package org.zerock.mallapi.infrastructure.todo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.todo.entity.Todo;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTodo() {
        Todo todo = Todo.builder()
                .title("title...")
                .content("content...")
                .complete(false)
                .dueDate(LocalDateTime.now())
                .build();

        todoRepository.save(todo);

        assertThat(todo.getId()).isNotNull();
    }
}
