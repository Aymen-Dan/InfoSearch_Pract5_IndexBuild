import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /**TODO: add user-friendly interface, like choosing how many blocks and having an ability to open up files*/
        // Example usage
        String directoryPath = "C:\\Users\\armad\\OneDrive\\Desktop\\IntelliJ IDEA Community Edition 2021.1.1\\IdeaProjects\\InfoSearch_Pract5_IndexBuild\\src\\res";
        int blockSize = 100;  // Adjust this based on your system's memory constraints

        Map<String, List<String>> index = BSBI_Index.buildBSBIIndex(directoryPath, blockSize);

       /**TODO: remove automatic console printOut*/
        // Print the final index
        for (Map.Entry<String, List<String>> entry : index.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}