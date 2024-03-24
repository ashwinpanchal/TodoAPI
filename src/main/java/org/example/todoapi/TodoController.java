package org.example.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<ApiResponse<Todo>> getTodoById(@PathVariable("id") int id){
        for(Todo todo : todoList){
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> deleteTodo(@PathVariable int id){
        for(int i=0;i<todoList.size();i++){
            if(todoList.get(i).getId() == id){
                Todo deletedTodo = todoList.remove(i);
                return ResponseEntity
                        .ok(new ApiResponse<>(deletedTodo,true, "Successfully deleted a Todo"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false, "Todo not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> updateTodoCompletely(@PathVariable int id, @RequestBody Todo todo){
        for(int i=0;i<todoList.size();i++){
            if(todoList.get(i).getId() == id){
                todo.setId(id);
                todoList.set(i, todo);
                return ResponseEntity
                        .ok(new ApiResponse<>(todo,true,"Successfully updated the todo"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false, "Todo not found"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> updateTodoPartial
            (@PathVariable int id,
             @RequestParam(required = false) String title,
             @RequestParam(required = false) Boolean completed,
             @RequestParam(required = false) Integer userId){
        for(Todo todo : todoList) {
            if(todo.getId() == id) {
                if(completed != null) {
                    todo.setCompleted(completed);
                }
                if(title != null) {
                    todo.setTitle(title);
                }
                if(userId != null) {
                    todo.setUserId(userId);
                }
                return ResponseEntity
                        .ok(new ApiResponse<>(todo, true, "Todo updated successfully"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false,"Todo not found"));
    }

}
