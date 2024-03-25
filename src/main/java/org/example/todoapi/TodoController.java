package org.example.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;

    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1L,false,"Todo 1", 1));
        todoList.add(new Todo(2L,true,"Todo 2",2));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Todo>>> getTodos(@RequestParam(required = false) Boolean isCompleted){
        if(Objects.equals(isCompleted,true)){
            return ResponseEntity
                    .ok(new ApiResponse<>
                            (todoList.stream().filter(n-> n.isCompleted()).collect(Collectors.toList())
                                    ,true,"Successfully fetched todo list"));
        }
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
        for(Todo todo : todoList){
            if(Objects.equals(todo.getId(), id)){
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
    public ResponseEntity<ApiResponse<Todo>> deleteTodo(@PathVariable Long id){
        for(int i=0;i<todoList.size();i++){
            if(Objects.equals(todoList.get(i).getId(), id)){
                Todo deletedTodo = todoList.remove(i);
                return ResponseEntity
                        .ok(new ApiResponse<>(deletedTodo,true, "Successfully deleted a Todo"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false, "Todo not found"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> updateTodoCompletely(@PathVariable Long id, @RequestBody Todo todo){
        for (Todo value : todoList) {
            if (Objects.equals(value.getId(), id)) {
                if (Objects.nonNull(todo.getTitle())) {
                    value.setTitle(todo.getTitle());
                }
                if (Objects.nonNull(todo.isCompleted())) {
                    value.setCompleted(todo.isCompleted());
                }
                if (Objects.nonNull(todo.getUserId())) {
                    value.setUserId(todo.getUserId());
                }
                return ResponseEntity
                        .ok(new ApiResponse<>(value, true, "Successfully updated the todo"));
            }
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null,false, "Todo not found"));
    }


}
