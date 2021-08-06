package io.github.gstfnk.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
class TodoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private final TodoRepository repository;

    TodoServlet(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos() {
        logger.info("Got request");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    Todo toggleTodo(@PathVariable Integer id) {
        Todo todo = repository.findById(id).get();
        todo.setDone(!todo.getDone());
        repository.save(todo);
        return todo;
    }

    @PostMapping
    Todo newTodo(@RequestBody Todo newTodo) {
        return repository.save(newTodo);
    }
}
