import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main Application.
 * <p>
 * You do not need to change this class.
 */
public class BTreeMain {
static int recordcount=0;
    public static void main(String[] args) {

        /** Read the input file -- input.txt */
        Scanner scan = null;
        try {
            scan = new Scanner(new File("src/input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        getStudents();
        /** Read the minimum degree of B+Tree first */

        int degree = scan.nextInt();

        BTree bTree = new BTree(degree);

        /** Reading the database student.csv into B+Tree Node*/
        List<Student> studentsDB = getStudents();

       for (Student s : studentsDB) {
            bTree.insert(s);
        }

        /** Start reading the operations now from input file*/
        try {
            while (scan.hasNextLine()) {
                Scanner s2 = new Scanner(scan.nextLine());

                while (s2.hasNext()) {

                    String operation = s2.next();
               
                    switch (operation) {
                        case "insert": {

                            long studentId = Long.parseLong(s2.next());
                            String studentName = s2.next() + " " + s2.next();
                            String major = s2.next();
                            String level = s2.next();
                            int age = Integer.parseInt(s2.next());
                            /** TODO: Write a logic to generate recordID*/
                            long recordID = recordcount;
                            recordcount++;
                            Student s = new Student(studentId, age, studentName, major, level, recordID);
                            bTree.insert(s);

                            break;
                        }
                        case "delete": {
                            long studentId = Long.parseLong(s2.next());
                            boolean result = bTree.delete(studentId);
                            if (result)
                                System.out.println("Student deleted successfully.");
                            else
                                System.out.println("Student deletion failed.");

                            break;
                        }
                        case "search": {
                            long studentId = Long.parseLong(s2.next());
                            long recordID = bTree.search(studentId);
                            if (recordID != -1)
                                System.out.println("Student exists in the database at " + recordID);
                            else
                                System.out.println("Student does not exist.");
                            break;
                        }
                        case "print": {
                            List<Long> listOfRecordID = new ArrayList<>();
                            listOfRecordID = bTree.print();
                            System.out.println("List of recordIDs in B+Tree " + listOfRecordID.toString());
                        }
                        default:
                            System.out.println("Wrong Operation");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }
    /**
     * Extracts the students information from "Students.csv"
     * return the list<Students>
     */
    private static List<Student> getStudents() {
    	Scanner scan = null;
    	try {
            scan = new Scanner(new File("src/Student.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    	
    	List<Student> studentList = new ArrayList<>();
    	while(scan.hasNext()) {
    	String Studentline = scan.nextLine();
    	String[] studentarray = Studentline.split(",");
    	//now time to stick in the parsing and the creation of the student
    		long studentId = Long.parseLong(studentarray[0]);
    		String studentName = studentarray[1];
    		String major = studentarray[2];
    		String level = studentarray[3];
    		int age = Integer.parseInt(studentarray[4]);
    		long recordID = Long.parseLong(studentarray[5]);
    	studentList.add(new Student(studentId,age,studentName,major,level,recordID));
    	recordcount++;
    	}
    	for(int i=0; i<studentList.size();i++) {
    		System.out.println(studentList.get(i));
    	}
    	
    	
        return studentList;
    }
    
}
