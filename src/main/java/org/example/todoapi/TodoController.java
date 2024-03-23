package org.example.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;

    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1", 1));
        todoList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return newTodo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") Long id){
        for(Todo todo: todoList){
            if(todo.getId() == id){
                return ResponseEntity.ok(todo);
            }
        }
        Map<String,String> response = new HashMap<>();
        response.put("message", "Todo Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
