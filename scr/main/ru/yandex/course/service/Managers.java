package main.ru.yandex.course.service;

import main.ru.yandex.course.model.HistoryManager;
import main.ru.yandex.course.model.InMemoryHistoryManager;
import main.ru.yandex.course.model.InMemoryTaskManager;
import main.ru.yandex.course.model.TaskManager;

public class Managers {

    public TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistory());
    }

    public HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
