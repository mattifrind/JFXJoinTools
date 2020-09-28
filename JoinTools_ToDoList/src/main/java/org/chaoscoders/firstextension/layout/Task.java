package org.chaoscoders.firstextension.layout;

public class Task {
    String description;
    Boolean done;

    public Task(String description, Boolean done) {
        this.description = description;
        this.done = done;
    }
    public Task(String description) {
        this.description = description;
    }
}
