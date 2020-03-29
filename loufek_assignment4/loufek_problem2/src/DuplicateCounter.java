import java.text.Format;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class DuplicateCounter {

    Map<String, Integer> wordCounter = new HashMap<>();

    public void count(String dataFile) {
        try(Scanner fileInput = new Scanner(Paths.get(dataFile))) {
            while(fileInput.hasNext()) { //checks for more input
                String fileWord = fileInput.next().toLowerCase(); //gets the file input
                if(wordCounter.containsKey(fileWord)) { //checks if it is currently in the map
                    int currentWordCount = wordCounter.get(fileWord); //Gets the current count of the word in question
                    wordCounter.put(fileWord, currentWordCount + 1); //increments the count
                } else {
                    wordCounter.put(fileWord, 1);
                }
            }
            fileInput.close(); //manually close system resources
        } catch (IOException e) {
            System.out.println("We're sorry, but an error has occurred\nError: Input file could not be found");
            System.exit(0);
        }
    }


    public void write(String outputFile) {
        File newOutputFile = new File(outputFile);
        try {
            newOutputFile.createNewFile();
        } catch (IOException e) {
            System.out.println("We're sorry, but an error has occurred\nError: Output file could not be created");
            System.exit(0);
        }

        Set<String> keys = wordCounter.keySet();
        TreeSet<String> sortedKeys = new TreeSet<>(keys);

        try(Formatter output = new Formatter(outputFile)) {
            output.format("Word\t    Word Count\n");
            for (String word : sortedKeys) {
                output.format("%-10s    %d%n", word, wordCounter.get(word));
            }
            output.close(); //manually close system resources
        } catch (FileNotFoundException e) {
            System.out.println("We're sorry, but an error has occurred\nError: Output file could not be found");
            System.exit(0);
        }
        System.out.println("Input file contents have been analyzed and the results have been printed to " + outputFile);
    }

}
