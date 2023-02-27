package com.furkanbulut.ToDoApp.Service;

import com.furkanbulut.ToDoApp.Model.ToDo;
import com.furkanbulut.ToDoApp.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo getToDoById(Integer id) {
        Optional<ToDo> optional = toDoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new IllegalArgumentException("ToDo with id " + id + " not found");
        }
    }

    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public ToDo updateToDoWithId(Integer id, ToDo toDo) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo existingToDo = optionalToDo.get();
            existingToDo.setTitle(toDo.getTitle());
            existingToDo.setDescription(toDo.getDescription());
            existingToDo.setDueDate(toDo.getDueDate());
            existingToDo.setCompleted(toDo.getCompleted());
            return toDoRepository.save(existingToDo);
        } else {
            return null;
        }
    }

    public ToDo deleteToDoById(Integer id) {
        Optional<ToDo> optionalTodo = toDoRepository.findById(id);

        if (optionalTodo.isPresent()) {

            toDoRepository.delete(optionalTodo.get());
            return optionalTodo.get();
        }
        return null;
    }
}
