package ru.yandex.course.model;

public class Subtask extends Task{
    private final int epicId;

    public Subtask(int epicId, String title, String description) {
        super(title, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "ru.yandex.course.model.Subtask{" +
                "epicId=" + epicId +
                "} " + super.toString();
    }
}
