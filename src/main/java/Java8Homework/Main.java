package Java8Homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();

        String inFileName = "src/main/resources/J8InputFIle.txt";
        int month = 3;
        String outFileName = "src/main/resources/J8OutputFile.txt";

        Path fileIn = new File(inFileName).toPath();
        try (BufferedReader reader = Files.newBufferedReader(fileIn)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                people.add(getPeopleFromFile(line));
            }
        } catch (IOException | ParseException e) {
            System.err.println("Exception: " + e);
        }
        System.out.println(people.size() + " people in list");

        List<String> resultedList = people.stream()
                .filter(p -> p.getMonthOfBirth() == month)
                .sorted(Comparator.comparing(Person::getLastName))
                .map(person1 -> person1.getFirstName() + " " + person1.getLastName())
                .collect(Collectors.toList());

        Path fileOut = new File(outFileName).toPath();
        try {
            Files.write(Paths.get(String.valueOf(fileOut)), resultedList);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }


    }




    public static Person getPeopleFromFile (String line) throws ParseException {
        String [] tokens = line.split(",");
        if (tokens.length != 3) {
            return null;
        }
        String firstName = tokens[0].trim();
        String lastName = tokens[1].trim();
        int dateOfBirth = extractMonth(tokens[2].trim());
        return new Person(firstName, lastName, dateOfBirth);
        }

    public static int extractMonth (String dateFromFile) throws ParseException {
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateFromFile);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;

    }

}
