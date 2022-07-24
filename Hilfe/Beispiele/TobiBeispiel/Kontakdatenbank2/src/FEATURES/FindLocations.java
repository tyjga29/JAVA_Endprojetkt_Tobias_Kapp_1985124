package FEATURES;

import DATA.Location;
import INTERFACES.FindArgs;

import java.util.HashMap;

public class FindLocations implements FindArgs {
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
        // INPUT STRING LOOKS LIKE THIS: --ortssuche="Markt"
        for(int i = 0; i<_args.length; i++)
        {
            if (_args[i].startsWith("--ortssuche=")) {
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
                //smth different
            }
        }
    }

    // FIND LOCATION BY NAME
    // Input: location name, locationHashMap
    public HashMap<Integer, Location> FindLocationsByName(String _name, HashMap<Integer, Location> _locationHashMap)
    {
        HashMap<Integer, Location> locationHashMap = new HashMap<>();

        if(returnArg != null)
        {
            System.out.println("Found this: ");
            for (int key : _locationHashMap.keySet()) {
                if (_locationHashMap.get(key).getName().toLowerCase().contains(_name.toLowerCase())) {
                    System.out.println(_locationHashMap.get(key).getName());
                    locationHashMap.put(_locationHashMap.get(key).getId(), _locationHashMap.get(key));
                }
            }
        }
        return locationHashMap;
    }
}
