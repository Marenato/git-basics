package test.ru.yandex.course.service;

import main.ru.yandex.course.model.TaskManager;
import main.ru.yandex.course.model.tasks.Epic;
import main.ru.yandex.course.model.tasks.Subtask;
import main.ru.yandex.course.model.tasks.Task;
import main.ru.yandex.course.service.Managers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class TaskManagersTest {
    public static TaskManager taskManager;

    @BeforeAll
    public static void generateTaskManager() {
        Managers managers = new Managers();
        TaskManager taskManager = managers.getDefault();

        Task task1 = new Task("Купить молоко", "2 литра");
        Task task2 = new Task("Выгулять собаку", "В парке");
        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("Ремонт", "В квартире");
        int epic1Id = taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask(epic1Id, "Купить краску", "Белая");
        Subtask subtask2 = new Subtask(epic1Id, "Нанять рабочих", "");
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);
        TaskManagersTest.taskManager = taskManager;
    }

    @BeforeEach
    public void deleteHistory() {
        taskManager.getHistoryManager().deleteHistory();
    }

    @Test
    public void historyTaskManagerTest() {
        taskManager.getTask(1);
        taskManager.getTask(2);
        taskManager.getSubtask(4);
        taskManager.getEpic(3);
        int historySize = taskManager.getHistoryManager().getHistory().size();
        Assertions.assertEquals(historySize, 4);
    }

    @Test
    public void notMore10SizeOfTaskManagerHistory() {
        for (int i = 0; i < 15; i++) {
            taskManager.getTask(1);
        }
        int historySize = taskManager.getHistoryManager().getHistory().size();
        Assertions.assertEquals(historySize, 10);
    }

    @Test
    public void rightListDirection() {
        taskManager.getTask(1);
        taskManager.getTask(2);
        taskManager.getEpic(3);
        taskManager.getSubtask(4);
        taskManager.getSubtask(5);
        List<Task> history = taskManager.getHistoryManager().getHistory();
        for (int i = 0; i < history.size(); i++) {
            Assertions.assertEquals(history.get(i).getId(), i + 1);
        }
    }


}