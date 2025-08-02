package main.ru.yandex.course.model;

import main.ru.yandex.course.model.tasks.Task;

import java.util.List;

public interface HistoryManager {

    List<Task> getHistory();

    void deleteHistory();

    void addTask(Task task);
}
