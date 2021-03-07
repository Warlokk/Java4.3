package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import ru.netology.domain.DateComparator;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueManager manager = new IssueManager(new IssueRepository());
    Issue first = new Issue(1, "Author1", 1614966504, true, Set.of("User1"), Set.of("Bug", "Invalid"),
            "Test", null, null);
    Issue second = new Issue(2, "Author2", 1614620903, true, Set.of("User1", "User2"), Set.of("Bug", "Invalid"),
            "Test", null, null);
    Issue third = new Issue(3, "Author1", 1615398503, false, Set.of("User2"), Set.of("Bug"),
            "Test", null, null);
    Issue fourth = new Issue(4, "Author1", 1615571303, true, Set.of("User2", "User3"), Set.of("Question", "Major"),
            "Test", null, null);
    Issue fifth = new Issue(5, "Author2", 1613152103, false, Set.of("User3"), Set.of("Question", "Invalid"),
            "Test", null, null);
    Issue sixth = new Issue(6, "Author1", 1614152103, true, Set.of("User3"), Set.of("Question", "Invalid"),
            "Test", null, null);

    @Nested
    public class Empty {

        @Test
        void shouldAddIssue() {
            manager.add(first);
            List<Issue> expected = List.of(first);
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowOpenedIssue() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowClosedIssue() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthor() {
            String filter = "Author1";
            List<Issue> expected = List.of();
            List<Issue> actual = manager.filterByAuthor(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            Set<String> filter = Set.of("Bug", "Invalid");
            List<Issue> expected = List.of();
            List<Issue> actual = manager.filterByLabel(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            Set<String> filter = Set.of("User1", "User2");
            List<Issue> expected = List.of();
            List<Issue> actual = manager.filterByAssignee(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

    }

    @Nested
    public class SingleItem {
        @BeforeEach
        void setUp() {
            manager.add(third);
        }

        @Test
        void shouldAddIssue() {
            manager.add(second);
            List<Issue> expected = List.of(third, second);
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowOpenedIssue() {
            List<Issue> expected = List.of();
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowClosedIssue() {
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthor() {
            String filter = "Author1";
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.filterByAuthor(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            Set<String> filter = Set.of("Bug");
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.filterByLabel(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            Set<String> filter = Set.of("User1", "User2");
            List<Issue> expected = List.of();
            List<Issue> actual = manager.filterByAssignee(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }


        @Test
        void shouldOpenById() {
            manager.openIssue(3);
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseById() {
            manager.closeIssue(3);
            List<Issue> expected = List.of(third);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

    }

    @Nested
    public class MultipleItem {
        @BeforeEach
        void setUp() {
            manager.add(first);
            manager.add(second);
            manager.add(third);
            manager.add(fourth);
            manager.add(fifth);
        }

        @Test
        void shouldAddIssue() {
            manager.add(sixth);
            List<Issue> expected = List.of(first, second, third, fourth, fifth, sixth);
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowOpenedIssue() {
            List<Issue> expected = List.of(first, second, fourth);
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldShowClosedIssue() {
            List<Issue> expected = List.of(third, fifth);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAuthor() {
            String filter = "Author1";
            List<Issue> expected = List.of(first, third, fourth);
            List<Issue> actual = manager.filterByAuthor(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByLabel() {
            Set<String> filter = Set.of("Bug", "Invalid");
            List<Issue> expected = List.of(first, second);
            List<Issue> actual = manager.filterByLabel(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterByAssignee() {
            Set<String> filter = Set.of("User1", "User2");
            List<Issue> expected = List.of(second);
            List<Issue> actual = manager.filterByAssignee(filter);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            List<Issue> expected = List.of(fourth, third, first, second, fifth);
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            List<Issue> expected = List.of(fifth, second, first, third, fourth);
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }


        @Test
        void shouldOpenById() {
            manager.openIssue(3);
            List<Issue> expected = List.of(first, second, third, fourth);
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnTryingOpenOpened() {
            manager.openIssue(1);
            List<Issue> expected = List.of(first, second, fourth);
            List<Issue> actual = manager.openedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseById() {
            manager.closeIssue(2);
            List<Issue> expected = List.of(second, third, fifth);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnTryingCloseClosed() {
            manager.closeIssue(3);
            List<Issue> expected = List.of(third, fifth);
            List<Issue> actual = manager.closedIssue();
            assertEquals(expected, actual);
        }

    }
}