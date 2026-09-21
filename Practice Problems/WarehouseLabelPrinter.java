public class WarehouseLabelPrinter {

    interface Printable {
        String printLabel();
    }

    static class PackageBox implements Printable {
        String trackingId;

        public PackageBox(String trackingId) {
            this.trackingId = trackingId;
        }

        @Override
        public String printLabel() {
            return "Package label: " + trackingId;
        }
    }

    static class Invoice implements Printable {
        String invoiceNumber;

        public Invoice(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        @Override
        public String printLabel() {
            return "Invoice label: " + invoiceNumber;
        }
    }

    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel());

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel());

        printAll(new Printable[]{p, i});
    }
}
