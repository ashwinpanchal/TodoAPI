package org.example.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ApiResponse<List<Todo>>> getTodos(){
        return ResponseEntity
                .ok(new ApiResponse<>(todoList,true,"Successfully fetched todo list"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Todo>> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(newTodo,true,"Successfully created a new Todo"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> getTodoById(@PathVariable("id") Long id){
        for(Todo todo: todoList){
            if(todo.getId() == id){
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(todo,true,"Successfully fetched todo"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false, "Todo not found"));
    }
}
