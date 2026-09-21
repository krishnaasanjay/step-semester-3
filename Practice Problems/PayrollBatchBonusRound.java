public class PayrollBatchBonusRound {

    static class Employee {
        String empId;
        double salary;

        Employee(String empId, double salary) {
            this.empId = empId;
            this.salary = salary;
        }

        void raiseSalary(double salary) {
            this.salary += salary;
        }
    }

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};

        for (int i = 0; i < empIds.length; i++) {
            Employee employee = new Employee(empIds[i], startingSalaries[i]);
            employee.raiseSalary(5000);
            System.out.println(employee.empId + " | Final Salary: Rs " + employee.salary);
        }
    }
}
