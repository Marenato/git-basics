package ru.yandex.course;

import ru.yandex.course.enumeration.TaskStatus;
import ru.yandex.course.model.Epic;
import ru.yandex.course.model.Subtask;
import ru.yandex.course.model.Task;
import ru.yandex.course.service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Купить молоко", "2 литра");
        Task task2 = new Task("Выгулять собаку", "В парке");
        int task1Id = manager.createTask(task1);
        int task2Id = manager.createTask(task2);

        Epic epic1 = new Epic("Ремонт", "В квартире");
        int epic1Id = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask(epic1Id, "Купить краску", "Белая");
        Subtask subtask2 = new Subtask(epic1Id, "Нанять рабочих", "");
        int subtask1Id = manager.createSubtask(subtask1);
        int subtask2Id = manager.createSubtask(subtask2);

        System.out.println("Задача 1: " + manager.getTask(task1Id).getTitle());
        System.out.println("Подзадача 1: " + manager.getSubtask(subtask1Id).getTitle());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks().size());

        System.out.println("Подзадачи эпика 1:");
        for (Subtask subtask : manager.getSubtasksByEpic(epic1Id)) {
            System.out.println("- " + subtask.getTitle());
        }

        task1.setStatus(TaskStatus.DONE);
        manager.updateTask(task1);

        epic1.setTitle("Ремонт комнаты");
        manager.updateEpic(epic1);

        subtask2.setDescription("Спросить у соседей");
        manager.updateSubtask(subtask2);

        System.out.println("\nПосле обновлений:");
        System.out.println("Статус задачи 1: " + manager.getTask(task1Id).getStatus());
        System.out.println("Название эпика: " + manager.getEpic(epic1Id).getTitle());
        System.out.println("Описание подзадачи 2: " +
                manager.getSubtask(subtask2Id).getDescription());

        manager.deleteTask(task2Id);
        manager.deleteSubtask(subtask1Id);
        System.out.println("\nПосле удаления:");
        System.out.println("Осталось задач: " + manager.getAllTasks().size());
        System.out.println("Осталось подзадач: " + manager.getAllSubtasks().size());

        manager.deleteAllSubtasks();
        System.out.println("Подзадач после deleteAllSubtasks: " +
                manager.getAllSubtasks().size());

        manager.deleteAllTasks();
        System.out.println("Задач после deleteAllTasks: " +
                manager.getAllTasks().size());

        manager.deleteAllEpics();
        System.out.println("Эпиков после deleteAllEpics: " +
                manager.getAllEpics().size());
    }
}