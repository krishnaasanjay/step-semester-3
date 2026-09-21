public class MonthlyAttendanceAnnouncer {

    static class GymMember {
        String memberId;
        int monthlyFee;
        int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
        }
    }

    static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();
        for (GymMember member : members) {
            announcement.append(member.displayInfo());
            if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                announcement.append(" [Trainer via downcast: ").append(premiumMember.trainerName).append("]");
            }
            announcement.append(" | ");
        }
        return announcement.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(members));

        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
            System.out.println(bad.trainerName);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
