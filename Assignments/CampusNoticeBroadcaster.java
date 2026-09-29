import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Q5. The Campus Notice Broadcaster
 * NoticeBoard depends only on the NotificationChannel interface, so a new
 * channel (e.g. WhatsApp) can be added without touching posting/delivery
 * logic. Notice validation (title + at least one department) lives on the
 * NoticeBoard, before any student is looked up.
 */
public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        String name();
        void send(Student student, String message);
    }

    static class EmailChannel implements NotificationChannel {
        public String name() { return "Email"; }
        public void send(Student student, String message) {
            System.out.println("[Email \u2192 " + student.name + "] " + message);
        }
    }

    static class SmsChannel implements NotificationChannel {
        public String name() { return "SMS"; }
        public void send(Student student, String message) {
            System.out.println("[SMS \u2192 " + student.name + "] " + message);
        }
    }

    static class AppChannel implements NotificationChannel {
        public String name() { return "App"; }
        public void send(Student student, String message) {
            System.out.println("[App \u2192 " + student.name + "] " + message);
        }
    }

    static class Student {
        final String name;
        final String department;
        final List<NotificationChannel> preferredChannels = new ArrayList<>();

        Student(String name, String department, NotificationChannel... channels) {
            this.name = name;
            this.department = department;
            for (NotificationChannel c : channels) preferredChannels.add(c);
        }
    }

    static class Notice {
        final String title;
        final Set<String> departments;

        Notice(String title, String... departments) {
            this.title = title;
            this.departments = new LinkedHashSet<>();
            for (String d : departments) this.departments.add(d);
        }
    }

    static class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        void enrol(Student student) { students.add(student); }

        void post(Notice notice) {
            if (notice.title == null || notice.title.isEmpty()) {
                System.out.println("Cannot post notice: A title is required.");
                return;
            }
            if (notice.departments.isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return;
            }

            System.out.println("Notice '" + notice.title + "' posted to " + String.join(", ", notice.departments) + ".");
            for (Student student : students) {
                if (notice.departments.contains(student.department)) {
                    for (NotificationChannel channel : student.preferredChannels) {
                        channel.send(student, notice.title);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        Student asha = new Student("Asha", "CSE", email, app);
        Student ravi = new Student("Ravi", "ECE", sms);
        board.enrol(asha);
        board.enrol(ravi);

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));
        board.post(new Notice("Sports Day"));
    }
}
