class Employee {
    int empId;
    String name;

    void setEmployeeDetails(int empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    void displayEmployeeDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + name);
    }
}

class Developer extends Employee {
    String programmingLanguage;

    void setDeveloperDetails(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    void displayDeveloperDetails() {
        displayEmployeeDetails();
        System.out.println("Programming Language: " + programmingLanguage);
    }
}

class Tester extends Employee {
    String testingTool;

    void setTesterDetails(String testingTool) {
        this.testingTool = testingTool;
    }

    void displayTesterDetails() {
        displayEmployeeDetails();
        System.out.println("Testing Tool: " + testingTool);
    }
}

public class inheritanceEmployeeManagement {
    public static void main(String[] args) {

        Developer dev = new Developer();
        dev.setEmployeeDetails(101, "Muskan");
        dev.setDeveloperDetails("Java");

        System.out.println("Developer Details");
        dev.displayDeveloperDetails();

        System.out.println();

        Tester tester = new Tester();
        tester.setEmployeeDetails(102, "Anjum");
        tester.setTesterDetails("Selenium");

        System.out.println("Tester Details");
        tester.displayTesterDetails();
    }
}