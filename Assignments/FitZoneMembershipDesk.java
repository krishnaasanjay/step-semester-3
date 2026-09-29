/**
 * Q4. The FitZone Membership Desk
 * MembershipPlan is an interface with its own calculateFee(), kept separate
 * from Membership's own status rules. Membership rejects any status change
 * that doesn't make sense (e.g. freezing an Expired membership).
 */
public class FitZoneMembershipDesk {

    static final double BASE_RATE_PER_MONTH = 1000;

    interface MembershipPlan {
        String name();
        int months();
        double calculateFee();
    }

    static class MonthlyPlan implements MembershipPlan {
        public String name() { return "Monthly"; }
        public int months() { return 1; }
        public double calculateFee() { return BASE_RATE_PER_MONTH * months(); }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public String name() { return "Quarterly"; }
        public int months() { return 3; }
        public double calculateFee() { return BASE_RATE_PER_MONTH * months() * 0.90; }
    }

    static class AnnualPlan implements MembershipPlan {
        public String name() { return "Annual"; }
        public int months() { return 12; }
        public double calculateFee() { return BASE_RATE_PER_MONTH * months() * 0.75; }
    }

    static class Member {
        final String name;
        Member(String name) { this.name = name; }
    }

    static class Membership {
        enum Status { ACTIVE, FROZEN, EXPIRED }

        final Member member;
        final MembershipPlan plan;
        private Status status;

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.status = Status.ACTIVE;
            System.out.println(plan.name() + " membership created for " + member.name
                    + ". Fee: \u20B9" + String.format("%,.2f", plan.calculateFee()) + ". Status: Active.");
        }

        void checkIn() {
            if (status != Status.ACTIVE) {
                System.out.println("Check-in denied: " + member.name + "'s membership is "
                        + capitalize(status) + ".");
                return;
            }
            System.out.println(member.name + " checked in successfully.");
        }

        void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if (status == Status.FROZEN) {
                System.out.println(member.name + "'s membership is already frozen.");
                return;
            }
            status = Status.FROZEN;
            System.out.println(member.name + "'s membership frozen. Status: Frozen.");
        }

        void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if (status != Status.FROZEN) {
                System.out.println(member.name + "'s membership is not frozen.");
                return;
            }
            status = Status.ACTIVE;
            System.out.println(member.name + "'s membership unfrozen. Status: Active.");
        }

        void expire() {
            status = Status.EXPIRED;
            System.out.println(member.name + "'s membership expired. Status: Expired.");
        }

        private static String capitalize(Status status) {
            String s = status.name().toLowerCase();
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}
