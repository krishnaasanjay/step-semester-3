import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Q2. The Assignment Submission Portal
 * Assignment is abstract with its own late-penalty rule, so a new
 * assignment type can be added without touching the grading workflow.
 * Submission guards its own status: Graded is final, no resubmission.
 */
public class AssignmentSubmissionPortal {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static abstract class Assignment {
        final String title;
        final int maxMarks;
        final LocalDate dueDate;

        Assignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        abstract double latePenaltyPercentPerDay();

        double applyLatePenalty(double awardedMarks, int daysLate) {
            if (daysLate <= 0) return awardedMarks;
            double penaltyPercent = Math.min(100, latePenaltyPercentPerDay() * daysLate);
            return awardedMarks * (1 - penaltyPercent / 100.0);
        }
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(String title, int maxMarks, LocalDate dueDate) { super(title, maxMarks, dueDate); }
        double latePenaltyPercentPerDay() { return 10; }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(String title, int maxMarks, LocalDate dueDate) { super(title, maxMarks, dueDate); }
        double latePenaltyPercentPerDay() { return 20; }
    }

    static class Submission {
        enum Status { SUBMITTED, GRADED }

        final Student student;
        final Assignment assignment;
        final LocalDate submittedOn;
        private Status status;
        private double finalMarks;

        Submission(Student student, Assignment assignment, LocalDate submittedOn) {
            this.student = student;
            this.assignment = assignment;
            this.submittedOn = submittedOn;
            this.status = Status.SUBMITTED;

            int daysLate = daysLate();
            String lateNote = daysLate > 0 ? " (" + daysLate + " day" + (daysLate > 1 ? "s" : "") + " late)" : " (on time)";
            System.out.println(student.name + "'s submission for '" + assignment.title + "' received"
                    + lateNote + ". Status: Submitted.");
        }

        int daysLate() {
            return (int) Math.max(0, ChronoUnit.DAYS.between(assignment.dueDate, submittedOn));
        }

        void grade(double awardedMarks) {
            if (status == Status.GRADED) {
                System.out.println("Cannot re-grade: '" + assignment.title + "' has already been graded.");
                return;
            }
            int daysLate = daysLate();
            finalMarks = assignment.applyLatePenalty(awardedMarks, daysLate);
            status = Status.GRADED;

            if (daysLate > 0) {
                double penaltyPercent = Math.min(100, assignment.latePenaltyPercentPerDay() * daysLate);
                System.out.println(student.name + " graded: " + (int) finalMarks + "/" + assignment.maxMarks
                        + " after " + (int) penaltyPercent + "% late penalty. Status: Graded.");
            } else {
                System.out.println(student.name + " graded: " + (int) finalMarks + "/" + assignment.maxMarks
                        + ". Status: Graded.");
            }
        }

        void resubmit() {
            if (status == Status.GRADED) {
                System.out.println("Cannot resubmit: '" + assignment.title + "' has already been graded.");
                return;
            }
            System.out.println(student.name + " resubmitted '" + assignment.title + "'.");
        }
    }

    public static void main(String[] args) {
        Assignment codingLab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2025, 3, 10));
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2025, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSub = new Submission(asha, codingLab, LocalDate.of(2025, 3, 10));
        Submission raviSub = new Submission(ravi, designEssay, LocalDate.of(2025, 3, 14));

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.resubmit();
    }
}
