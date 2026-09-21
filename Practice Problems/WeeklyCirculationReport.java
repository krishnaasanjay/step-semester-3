public class WeeklyCirculationReport {

    static class LibraryMember {
        String memberId;
        int borrowLimit;
        int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        String displayInfo() {
            return "General | Books: " + booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {
        String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        String displayInfo() {
            return "Student | Course: " + course + " | Books: " + booksBorrowed;
        }
    }

    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();
        for (LibraryMember member : members) {
            report.append(member.displayInfo());
            if (member instanceof StudentMember) {
                StudentMember studentMember = (StudentMember) member;
                report.append(" [Course via downcast: ").append(studentMember.course).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(members));

        LibraryMember plain = new LibraryMember("LB6", 3);
        try {
            StudentMember bad = (StudentMember) plain;
            System.out.println(bad.course);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
