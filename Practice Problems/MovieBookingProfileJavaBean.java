public class MovieBookingProfileJavaBean {

    static class MovieBookingProfile {
        private String name;
        private boolean confirmed;
        private int otpHash;

        public MovieBookingProfile() {
        }

        public MovieBookingProfile(String name) {
            this();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public void setOtp(String otp) {
            this.otpHash = otp == null ? 0 : otp.hashCode();
        }
    }

    public static void main(String[] args) {
        System.out.println(new MovieBookingProfile("Rahul Dev").getName());

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
        System.out.println("OTP stored (no getter available)");
    }
}
