package com.furkanbulut.ToDoApp;

import com.furkanbulut.ToDoApp.Model.ToDo;
import com.furkanbulut.ToDoApp.Repository.ToDoRepository;
import com.furkanbulut.ToDoApp.Service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllToDos() {
        // Arrange
        List<ToDo> expectedToDos = new ArrayList<>();
        expectedToDos.add(new ToDo(1, "Title 1", "Description 1", LocalDate.now(), false));
        expectedToDos.add(new ToDo(2, "Title 2", "Description 2", LocalDate.now().plusDays(1), false));
        when(toDoRepository.findAll()).thenReturn(expectedToDos);

        // Act
        List<ToDo> actualToDos = toDoService.getAllToDos();

        // Assert
        assertEquals(expectedToDos, actualToDos);
        verify(toDoRepository, times(1)).findAll();
    }

    @Test
    public void testGetToDoById() {
        // Arrange
        ToDo expectedToDo = new ToDo(1, "Title 1", "Description 1", LocalDate.now(), false);
        when(toDoRepository.findById(1)).thenReturn(Optional.of(expectedToDo));

        // Act
        ToDo actualToDo = toDoService.getToDoById(1);

        // Assert
        assertEquals(expectedToDo, actualToDo);
        verify(toDoRepository, times(1)).findById(1);
    }

    @Test
    public void testGetToDoById_NotFound() {
        // Arrange
        when(toDoRepository.findById(1)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> toDoService.getToDoById(1));
        verify(toDoRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateToDo() {
        // Arrange
        ToDo toDoToSave = new ToDo(null, "Title", "Description", LocalDate.now(), false);
        ToDo expectedToDo = new ToDo(1, "Title", "Description", LocalDate.now(), false);
        when(toDoRepository.save(toDoToSave)).thenReturn(expectedToDo);

        // Act
        ToDo actualToDo = toDoService.createToDo(toDoToSave);

        // Assert
        assertEquals(expectedToDo, actualToDo);
        verify(toDoRepository, times(1)).save(toDoToSave);
    }

    @Test
    public void testUpdateToDoWithId() {
        // Arrange
        ToDo existingToDo = new ToDo(1, "Title 1", "Description 1", LocalDate.now(), false);
        ToDo toDoToUpdate = new ToDo(1, "Updated Title", "Updated Description", LocalDate.now().plusDays(1), true);
        when(toDoRepository.findById(1)).thenReturn(Optional.of(existingToDo));
        when(toDoRepository.save(existingToDo)).thenReturn(toDoToUpdate);

        // Act
        ToDo actualToDo = toDoService.updateToDoWithId(1, toDoToUpdate);

        // Assert
        assertEquals(toDoToUpdate, actualToDo);
        verify(toDoRepository, times(1)).findById(1);
        verify(toDoRepository, times(1)).save(existingToDo);
    }
    @Test
    public void testDeleteToDoById() {
        // Arrange
        ToDo toDoToDelete = new ToDo(1, "Title 1", "Description 1", LocalDate.now(), false);
        when(toDoRepository.findById(1)).thenReturn(Optional.of(toDoToDelete));

        // Act
        ToDo deletedToDo = toDoService.deleteToDoById(1);

        // Assert
        assertEquals(toDoToDelete, deletedToDo);
        verify(toDoRepository, times(1)).findById(1);
        verify(toDoRepository, times(1)).delete(toDoToDelete);
    }
}
