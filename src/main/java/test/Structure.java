package test;

import javax.persistence.Persistence;

public class Structure
{
    public static void main(String[] args)
    {

        Persistence.generateSchema("CA2pu", null);
      
    }
}