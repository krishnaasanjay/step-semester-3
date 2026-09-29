import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q3. Online Examination System
 * Each Question subtype evaluates itself, so Attempt.submit() never needs to
 * know which types exist. A submitted Attempt rejects all further changes.
 */
public class OnlineExamSystem {

    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static abstract class Question {
        final int number;
        final String text;
        final int points;

        Question(int number, String text, int points) {
            this.number = number;
            this.text = text;
            this.points = points;
        }

        abstract boolean isCorrect(String answer);
    }

    static class MultipleChoiceQuestion extends Question {
        private final String correctOption;
        MultipleChoiceQuestion(int number, String text, int points, String correctOption) {
            super(number, text, points);
            this.correctOption = correctOption;
        }
        boolean isCorrect(String answer) { return correctOption.equalsIgnoreCase(answer.trim()); }
    }

    static class TrueFalseQuestion extends Question {
        private final boolean correct;
        TrueFalseQuestion(int number, String text, int points, boolean correct) {
            super(number, text, points);
            this.correct = correct;
        }
        boolean isCorrect(String answer) { return Boolean.parseBoolean(answer.trim()) == correct; }
    }

    static class ShortAnswerQuestion extends Question {
        private final String expected;
        ShortAnswerQuestion(int number, String text, int points, String expected) {
            super(number, text, points);
            this.expected = expected;
        }
        boolean isCorrect(String answer) { return expected.equalsIgnoreCase(answer.trim()); }
    }

    static class Examination {
        final String name;
        private final List<Question> questions = new ArrayList<>();
        private final Set<Student> submittedBy = new HashSet<>();

        Examination(String name) { this.name = name; }

        void addQuestion(Question q) { questions.add(q); }
        List<Question> getQuestions() { return questions; }
        int totalPoints() { return questions.stream().mapToInt(q -> q.points).sum(); }
        boolean hasSubmitted(Student s) { return submittedBy.contains(s); }
        void markSubmitted(Student s) { submittedBy.add(s); }

        Attempt start(Student student) {
            if (hasSubmitted(student)) {
                System.out.println(student.name + " has already submitted " + name + ".");
                return null;
            }
            System.out.println(name + " started by " + student.name + ".");
            return new Attempt(student, this);
        }
    }

    static class Attempt {
        private final Student student;
        private final Examination exam;
        private final Map<Integer, String> answers = new HashMap<>();
        private boolean submitted = false;

        Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
        }

        void answer(int questionNumber, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            answers.put(questionNumber, answer);
            System.out.println("Answer recorded for Question " + questionNumber + ".");
        }

        void submit() {
            if (submitted) {
                System.out.println("Examination already submitted.");
                return;
            }
            submitted = true;
            exam.markSubmitted(student);
            System.out.println(exam.name + " submitted by " + student.name + ".");

            StringBuilder result = new StringBuilder("Result: ");
            int score = 0;
            List<Question> qs = exam.getQuestions();
            for (int i = 0; i < qs.size(); i++) {
                Question q = qs.get(i);
                String given = answers.get(q.number);
                boolean correct = given != null && q.isCorrect(given);
                int earned = correct ? q.points : 0;
                score += earned;
                result.append("Question ").append(q.number).append(": ")
                        .append(correct ? "Correct" : "Incorrect")
                        .append(" (").append(earned).append(" points)");
                result.append(i < qs.size() - 1 ? ", " : ". ");
            }
            result.append("Total score: ").append(score).append("/").append(exam.totalPoints()).append(".");
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion(1, "Which keyword prevents overriding?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion(2, "Java supports multiple class inheritance.", 5, false));

        Student student1 = new Student("Student 1");
        Attempt attempt = examA.start(student1);
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();
        attempt.answer(1, "A");
    }
}
