import java.io.IOException;
import java.util.Scanner;

public class admin {

    public static void main(String[] args) throws IOException {

        System.out.println("\n" + "Please select an option" + "\n" + "1) View student info" + "\n" + "2) Add student"
                + "\n" + "3) Delete Student" + "\n" + "4) Print all student info");

        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        Boolean continueOrNot = true; // Allows for multiple inputs in a given case
                                      // and exporting data after all inputs

        Boolean firstLaunch = true; // Ensures hashmap is imported once, when a case is first selected.
                                    // Avoiding importing and exporting after every change to the hashmap
        int answer;

        switch (option) {
            case 1:
                while (continueOrNot) {
                    System.out.println("Enter the name of the student" + "\n");
                    String name = scan.next();
                    System.out.println(student.getInfo(name) + "\n");
                    System.out.println("Would you like to search for another student?");
                    System.out.println("Enter 1 for yes or 0 for no");
                    answer = scan.nextInt();
                    if (answer == 0) {
                        continueOrNot = false;
                    }
                }
                break;

            case 2:
                while (continueOrNot) {
                    while (firstLaunch) {

                        student.importStudentMap();
                        firstLaunch = false;
                    }
                    System.out.println("\n" + "Please enter the name of student");
                    String name = scan.next();
                    System.out.println("Please enter student info");
                    String info = scan.next();
                    student newstudent = new student(name, info);
                    System.out.println("Would you like to add another student?" + "\n");
                    System.out.println("Enter 1 for yes or 0 to save and exit");
                    answer = scan.nextInt();
                    if (answer == 0) {
                        student.exportStudentMap();
                        continueOrNot = false;
                    }
                }
                break;

            case 3:
                while (continueOrNot) {
                    while (firstLaunch) {

                        student.importStudentMap();
                        firstLaunch = false;
                    }
                    System.out.println("\n" + "Enter the name of the student to delete");
                    String name = scan.next();
                    student.deleteStudent(name);
                    System.out.println("Would you like to delete another student?");
                    System.out.println("Enter 1 for yes or 0 to save and exit");
                    answer = scan.nextInt();
                    if (answer == 0) {
                        student.exportStudentMap();
                        continueOrNot = false;
                    }
                }
                break;

            case 4:
                student.printAllStudentData();
                break;
        }

    }
}