package FEATURES;

import DATA.Person;
import INTERFACES.FindArgs;

import java.util.HashMap;

public class FindPersons implements FindArgs {
    // VARIABLES
    // --------------------------------------------------------------------------------
    private String returnArg;

    // GETTER/SETTER
    // --------------------------------------------------------------------------------
    public String getReturnArg() {
        return returnArg;
    }

    // METHODS
    // --------------------------------------------------------------------------------
    @Override
    public void FindArgs(String[] _args)
    {
        // INPUT STRING LOOKS LIKE THIS: --personensuche="ila"
        for(int i = 0; i<_args.length; i++)
        {
            if (_args[i].startsWith("--personensuche=")) {
                String[] stringsPersonSearch = _args[i].split("=");
                returnArg = stringsPersonSearch[1].replace("\"", "");
            }
            else if(returnArg == null)
            {
                // cant find a right param
                returnArg = null;
            }
            else
            {
                // smth different
            }
        }
    }

    // FIND PERSON BY NAME:
    // Input: name, personHashMap
    public HashMap<Integer,Person> FindPersonsByName(String _name, HashMap<Integer, Person> _personHashMap)
    {
        HashMap<Integer, Person> returnPersonHashMap = new HashMap<>();

        if(_name != null) {
            System.out.println("Found this: ");
            for (int key : _personHashMap.keySet()) {
                if (_personHashMap.get(key).getName().toLowerCase().contains(_name.toLowerCase())) {
                    System.out.println(_personHashMap.get(key).getName());
                    returnPersonHashMap.put(_personHashMap.get(key).getId(), _personHashMap.get(key));
                }
            }
        }
        return returnPersonHashMap;
    }

}
