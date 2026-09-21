public class MembershipNumbersRenewalCodesNightlyAudit {

    static class LibraryMember {
        static int counter = 100;
        static int membersEnrolled = 0;

        final String memberNumber;
        int borrowLimit;
        int booksBorrowed;

        public LibraryMember(int borrowLimit) {
            counter++;
            this.memberNumber = "LIB-" + counter;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
            membersEnrolled++;
        }

        void borrowBook() {
            booksBorrowed++;
        }

        void borrowBook(String genre) {
            borrowBook();
        }

        int getBooksBorrowed() {
            return booksBorrowed;
        }

        static boolean isValidRenewalCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }
            if (code.charAt(0) != 'R') {
                return false;
            }
            if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
                return false;
            }
            return Character.isUpperCase(code.charAt(3));
        }

        static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    static class FacultyMember extends LibraryMember {
        String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }
    }

    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
                faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] members = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println(processNightlyAudit(members));
    }
}
