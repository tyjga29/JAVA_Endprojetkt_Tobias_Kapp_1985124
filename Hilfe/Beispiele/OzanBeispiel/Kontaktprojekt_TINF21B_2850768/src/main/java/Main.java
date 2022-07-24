import services.ArgumentService;
import services.DataService;

import java.net.URL;

public class Main {

    public static void main(String[] args) {
        URL fileUrl = Main.class.getClassLoader().getResource("contacts2022.txt");
        DataService dataService = new DataService(fileUrl);
        new ArgumentService().handleArguments(args, dataService);
    }
}
