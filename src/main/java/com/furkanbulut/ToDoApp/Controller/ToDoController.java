package com.furkanbulut.ToDoApp.Controller;

import com.furkanbulut.ToDoApp.Model.ToDo;
import com.furkanbulut.ToDoApp.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("")
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> toDos = toDoService.getAllToDos();
        return ResponseEntity.ok().body(toDos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Integer id) {
        ToDo toDo = toDoService.getToDoById(id);
        if (toDo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(toDo);
    }

    @PostMapping("")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        ToDo newToDo = toDoService.createToDo(toDo);
        return ResponseEntity.ok().body(newToDo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Integer id, @RequestBody ToDo toDo) {
        ToDo updatedToDo = toDoService.updateToDoWithId(id, toDo);
        if (updatedToDo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedToDo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ToDo> deleteToDoById(@PathVariable Integer id) {
        ToDo deletedToDo = toDoService.deleteToDoById(id);
        if (deletedToDo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(deletedToDo);
    }
}
