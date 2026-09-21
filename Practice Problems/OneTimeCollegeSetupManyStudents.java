public class OneTimeCollegeSetupManyStudents {

    static class SrmStudent {
        static String collegeName;
        static String academicYear;
        String studentName;

        static {
            collegeName = "SRM Institute of Science and Technology";
            academicYear = "2025-2026";
            System.out.println("College info loaded");
        }

        SrmStudent(String studentName) {
            this.studentName = studentName;
        }
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            SrmStudent student = new SrmStudent(name);
            System.out.println("Student record created: " + student.studentName);
        }
    }
}
