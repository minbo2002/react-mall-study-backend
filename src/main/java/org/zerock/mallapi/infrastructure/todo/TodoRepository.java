package org.zerock.mallapi.infrastructure.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.domain.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
