package ru.netology.domain;

import java.util.Comparator;

public class DateComparator implements Comparator<Issue> {

    public int compare(Issue i1, Issue i2) {
        return i1.getDate() - i2.getDate();
    }
}
