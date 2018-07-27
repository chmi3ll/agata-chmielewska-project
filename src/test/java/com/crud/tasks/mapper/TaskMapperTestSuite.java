package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        TaskDto taskDto = new TaskDto(2L, "tytul", "cos_tam");
        Task result = taskMapper.mapToTask(taskDto);
        Assert.assertEquals("tytul", result.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        Task task = new Task(2L, "tytul", "cos_tam");
        TaskDto result = taskMapper.mapToTaskDto(task);
        Assert.assertEquals("tytul", result.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(2L, "tytul", "cos_tam"));
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);
        Assert.assertEquals(1, result.size());
    }
}
