package services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ArgumentService {
    private DataService dataService;

    /**
     * Handle Arguments based on argumentType and execute function from the DataService
     * @param args
     * @param dataService
     */
    public void handleArguments(String[] args, DataService dataService){
        this.dataService = dataService;
        String rawData = args[0];
        //beautify the rawData by removing parenthesis
        rawData = rawData.replace("\"", "");
        String[] data = rawData.split("=");
        String argumentType = data[0];
        //switch case based on argumentType and parse rawData
        switch(argumentType){
            case "--personensuche":
                handlePeople(data[1]);
                break;
            case "--ortssuche":
                handleLocations(data[1]);
                break;
            case "--kontaktpersonen":
                handleContacts(Integer.parseInt(data[1]));
                break;
            case "--besucher":
                String[] intel = data[1].split(",");
                handleVisits(Integer.parseInt(intel[0]), LocalDateTime.parse(intel[1]));
                break;
            default:
                break;

        }
    }

    /**
     * execute searchForPeople and map the resulting List<Person> to a String containing all the Names and handle the String output
     * @param query searchQuery that gets compared to the Person name
     */
    private void handlePeople(String query){
        String output = dataService.searchForPeople(query).stream().map(data -> data.getName() +", ").collect(Collectors.joining()).strip();
        stringOutput(output);
    }

    /**
     * execute searchForLocations and map the resulting List<Location> to a String containing all the Names and handle the String output
     * @param query searchQuery that gets compared to the Person name
     */
    private void handleLocations(String query){
        String output = dataService.searchForLocations(query).stream().map(data -> data.getName() +", ").collect(Collectors.joining()).strip();
        stringOutput(output);
    }

    /**
     * execute searchForContacts and map the resulting List<Person> to a String containing all the Names and handle the String output
     * @param personId Id to find the contacts of
     */
    private void handleContacts(Integer personId){
        String output = dataService.searchForContacts(personId).stream().map(person -> person.getName() +", ").collect(Collectors.joining()).strip();
        stringOutput(output);
    }

    /**
     * execute searchForVisitors and map the resulting List<Person> to a String containing all the Names and handle the String output
     * @param locationId locationId to identify the Location
     * @param time time to identify the Timeframe
     */
    private void handleVisits(Integer locationId, LocalDateTime time){
        String output = dataService.searchForVisitors(locationId, time).stream().map(person -> person.getName() +", ").collect(Collectors.joining()).strip();
        stringOutput(output);
    }

    /**
     * beautify String and print it
     * @param output
     */
    private void stringOutput(String output){
        //removes the last comma, if it exists
        if(output.endsWith(",")){
            output = output.substring(0, output.length()-1);
        }
        //output the beautified string
        System.out.println(output);
    }
}
