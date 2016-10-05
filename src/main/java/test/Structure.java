package test;

import java.util.HashMap;
import javax.persistence.Persistence;

public class Structure
{
    public static void main(String[] args)
    {
        
        
        
       HashMap<String, Object> puproperties = new HashMap();
        
        puproperties.put("javax.persistence.sql-load-script-source", "scripts/populateZip.sql");
        
        Persistence.generateSchema("CA2pu", null);
       // Persistence.generateSchema("CA2pu", puproperties);        
    }
}