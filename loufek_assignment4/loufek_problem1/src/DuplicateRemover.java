import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class DuplicateRemover {

    ArrayList<String> words;
    Set<String> uniqueWords;

    DuplicateRemover() {
        words = new ArrayList<>();
    }


    public void write(String outputFile) {
        File newOutputFile = new File(outputFile);
        try {
            newOutputFile.createNewFile(); //Creates new file
        } catch (IOException e) {
            System.out.println("We're sorry, but an error has occurred\nError: The output file could not be created");
            System.exit(0);
        }

        try(Formatter output = new Formatter(outputFile)) {
            for(String word : uniqueWords) {
                output.format("%s%n", word);
            }
            output.close(); //Manually close system resources
        } catch (FileNotFoundException e) {
            System.out.println("We're sorry, but an error has occurred\nError: The output file could not be found");
            System.exit(0);
        }
        System.out.println("Duplicates have been removed and the results have been printed to " + outputFile);
    }


    public void remove(String dataFile) {
        try(Scanner fileInput = new Scanner(Paths.get(dataFile))) { //Tries to open the file path. Terminates if the file cannot be found
            while(fileInput.hasNext()) {
                words.add(fileInput.next().toLowerCase());
            }
            fileInput.close(); //Manually closes system resources
        } catch (IOException e) {
            System.out.println("We're sorry, but an error has occurred\nError: The input file could not be found");
            System.exit(0);
        }

        uniqueWords = new HashSet<>(words); //HashSets cannot have duplicates. Effectively removing all duplicates
    }
}



/*
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class DuplicateRemover {

    ArrayList<String> words;
    ArrayList<String> uniqueWords;

    DuplicateRemover() {

    }

    public void remove(String dataFile) {
        words = readFile(dataFile);
        System.out.println(words);
        uniqueWords = removeDuplicates(words);
        System.out.println(uniqueWords);
    }

    public void write(String outputFile) {
        File newOutputFile = new File(outputFile);
        try {
            newOutputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Formatter output = new Formatter(outputFile)) {
            for(String word : uniqueWords) {
                output.format("%s%n", word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFile(String dataFile) {
        ArrayList<String> words = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get(dataFile))) {
            while(input.hasNext()) {
                words.add(input.next().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public ArrayList<String> removeDuplicates(ArrayList<String> listWithDuplicates) {
        ArrayList<String> noDuplicates = new ArrayList<>();
        for(String word : listWithDuplicates) {
            if(!noDuplicates.contains(word)) noDuplicates.add(word);
        }
        return noDuplicates;
    }
}
 */