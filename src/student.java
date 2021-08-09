import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

public class student implements Serializable {

    public static Map<String, ArrayList<String>> studentMap = new HashMap<String, ArrayList<String>>();
    static File dataFile = new File("dataFile.txt");
    static FileOutputStream fOut = null;
    static ObjectOutputStream oOut = null;
    static FileInputStream fIn = null;
    static ObjectInputStream oIn = null;
    String name;
    String info;

    student(String name, String info) {
        this.name = name;
        this.info = info;
        studentMap.put(this.name, new ArrayList<String>());
        studentMap.get(this.name).add(this.info);
        System.out.println("\n" + "****STUDENT ADDED****" + "\n");
    }

    public static void importStudentMap() throws IOException {
        if (dataFile.length() == 0) { // hashmapless dataFile would exist if exportStudentMap function
                                      // never exectues after initial dataFile creation
            dataFile.delete();
        }
        if (dataFile.createNewFile()) {
            System.out.println("Creating data file..." + "\n");
        } else {
            try {
                fIn = new FileInputStream(dataFile);
                oIn = new ObjectInputStream(fIn);
                studentMap = (Map<String, ArrayList<String>>) oIn.readObject();
                oIn.close();
                fIn.close();
            } catch (IOException ioe) {
                System.out.println("an error has occured");
                ioe.printStackTrace();
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Class not found");
                cnfe.printStackTrace();
            }
        }

    }

    public static void exportStudentMap() throws FileNotFoundException {

        PrintWriter deleteOldData = new PrintWriter("dataFile.txt");
        deleteOldData.print(""); // deletes previous hashmap since readObject reads first line of txt file
        deleteOldData.close();
        try {
            fOut = new FileOutputStream(dataFile, true);
            oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(studentMap);
            oOut.close();
            fOut.close();
            System.out.println("\n" + "****STUDENT DATA UPDATED****");
        } catch (Exception e) {
            System.out.println("an error has occured");
        }

    }

    public static ArrayList<String> getInfo(String name) throws IOException {
        importStudentMap(); // imports student hashmap and returns value of given name key
        return studentMap.get(name);
    }

    public static void deleteStudent(String name) throws IOException {
        studentMap.remove(name); // removes KV of given key from already imported hashmap
        System.out.println("\n" + "****STUDENT DATA DELETED****");
    }

    public static void printAllStudentData() throws IOException {
        if (dataFile.length() == 0) { // avoids an error if there is a hashmapless dataFile and no objects to read
            System.out.println("Add students to view student info");
        } else {
            importStudentMap();
            for (Map.Entry<String, ArrayList<String>> entry : studentMap.entrySet()) {
                String key = entry.getKey(); // Loops through hashmap printing out all KV
                ArrayList<String> value = entry.getValue();
                System.out.println(key + ": " + value);

            }

        }

    }

}
