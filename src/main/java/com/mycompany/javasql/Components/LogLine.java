package com.mycompany.javasql.Components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Status {
    SUCCESS, ERROR
}

public class LogLine {
    private final int id;
    private final LocalDate time;
    private final Status status;
    private final String query;
    private final String message;

    public LogLine(int id, Status status, String query, String message) {
        this.id = id;
        this.time = LocalDate.now();
        this.status = status;
        this.query = query;
        this.message = message;
    }

    public String[] getLine() {
        String[] line = new String[5];

        line[0] = String.valueOf(this.id);
        line[1] = this.time.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
        line[2] = this.status.toString();
        line[3] = this.query;
        line[4] = this.message;

        return line;
    }
}
