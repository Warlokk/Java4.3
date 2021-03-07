package ru.netology.domain;

import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    private int id;
    private String author;
    private int date;
    private boolean isOpen;
    private Set<String> assignees;
    private Set<String> labels;
    private String projects;
    private String milestones;
    private Set<String> participants;


}
