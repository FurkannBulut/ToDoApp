package com.furkanbulut.ToDoApp.Repository;

import com.furkanbulut.ToDoApp.Model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

}
