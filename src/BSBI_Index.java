import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BSBI_Index {

    /**TODO: add MORE files to collection*/

    // Tokenize a document into terms
    private static List<String> tokenize(String document) {
        // You may want to improve this based on your specific needs
        String[] terms = document.toLowerCase().split("\\s+");
        return Arrays.asList(terms);
    }

    // BSBI Index building method
    public static Map<String, List<String>> buildBSBIIndex(String directoryPath, int blockSize) {
        Map<String, List<String>> termDict = new HashMap<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("No files found in the specified directory.");
            return termDict;
        }

        // Step 1: Partitioning
        int numFiles = files.length;
        int numBlocks = (int) Math.ceil((double) numFiles / blockSize);

        for (int blockNum = 0; blockNum < numBlocks; blockNum++) {
            int startIdx = blockNum * blockSize;
            int endIdx = Math.min((blockNum + 1) * blockSize, numFiles);
            File[] blockFiles = Arrays.copyOfRange(files, startIdx, endIdx);

            List<Map.Entry<String, String>> blockTerms = new ArrayList<>();

            // Step 2: Tokenization and Sorting for each block
            for (File file : blockFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    StringBuilder document = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        document.append(line).append(" ");
                    }

                    List<String> terms = tokenize(document.toString());
                    for (String term : terms) {
                        blockTerms.add(new AbstractMap.SimpleEntry<>(term, file.getName()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Sort terms within the block
            blockTerms.sort(Comparator.comparing(Map.Entry::getKey));

            // Step 3: Merging
            for (Map.Entry<String, String> entry : blockTerms) {
                String term = entry.getKey();
                String fileName = entry.getValue();

                termDict.computeIfAbsent(term, k -> new ArrayList<>()).add(fileName);
            }
        }

        // Step 4: Final Index Creation
        Map<String, List<String>> finalIndex = new HashMap<>();
        termDict.forEach((term, fileNames) -> finalIndex.put(term, new ArrayList<>(new HashSet<>(fileNames))));

        /**TODO: save statistics into statistics.txt and index into index.txt*/
        return finalIndex;
    }

}
