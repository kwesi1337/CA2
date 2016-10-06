package facade;

import entity.Person;
import entity.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

    
public class Facade
{

    
    EntityManagerFactory emf;

    public Facade()
    {
    }

    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public EntityManagerFactory getEmf()
    {
        return emf;
    }

    public Facade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public Person addPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        }
        catch (Exception e)
        {
            System.out.println("Error" + e);
        }
        finally
        {
            em.close();
        }
        return p;
    }

    public Person deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }

    public Person editPerson(Person pers)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Person p = em.find(Person.class, pers.getId());
            if (p != null)
            {
                p = pers;
                em.merge(p);
                em.getTransaction().commit();
                return p;
            }
        }
        finally
        {
            em.close();
        }

        return null;
    }

    public Person getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        Person p = null;

        try
        {
            em.getTransaction().begin();
            p = em.find(Person.class, id);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }

    public List<Person> getPersons()
    {
        EntityManager em = emf.createEntityManager();

        List<Person> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    public List<Person> getPersons(String hobby)
    {
        EntityManager em = emf.createEntityManager();

        List<Person> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p where hobby=" + hobby).getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    public List<Person> getPersons(int zipCode)
    {
        EntityManager em = emf.createEntityManager();

        List<Person> persons = null;

        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p where Zip=" + zipCode).getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    public Company getCompanyById(int id)
    {
        EntityManager em = emf.createEntityManager();

        Company c = null;

        try
        {
            em.getTransaction().begin();
            c = em.find(Company.class, id);
            em.getTransaction().commit();
            return c;
        }
        finally
        {
            em.close();
        }
    }
    
    public Company getCompanyByCvr(int cvr)
    {
        EntityManager em = emf.createEntityManager();

        Company c = null;

        try
        {
            em.getTransaction().begin();
            c = em.find(Company.class, cvr);
            em.getTransaction().commit();
            return c;
        }
        finally
        {
            em.close();
        }
    }
    
    public List<Company> getCompanies()
    {
        EntityManager em = emf.createEntityManager();
        
        List<Company> c = null;
        try{
            em.getTransaction().begin();
            c = em.createQuery("select c from Company c", Company.class).getResultList();
            em.getTransaction().commit();
            
            return c;
        } finally{
            em.close();
        }
    }

    public Company getCompanyByPhone(String phoneNumber) 
    {
        EntityManager em = emf.createEntityManager();
        
        Company c = null;
        try{
            em.getTransaction().begin();
            Query q = em.createQuery("Select c from Company c join c.phones ph where ph.phoneNumber =" + phoneNumber, Company.class);
            q.setParameter("phoneNumber", phoneNumber);
            
            c = (Company) q.getSingleResult();
            
            return c;
            
        } finally {
            em.close();
        }
    }
     
    public List<Company> getCompaniesByEmp(int number)
    {
        EntityManager em = emf.createEntityManager();
        
        List<Company> c = null;
        try{
            
            TypedQuery<Company> q = em.createQuery("select c from Company c where c.numEmployees >=" + number, Company.class);
            q.setParameter("number", number);
            
            c = q.getResultList();
            
            return c;
        } finally {
            em.close();
        }
    }
    
    public Company addCompany(Company c) 
    {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            
            return c;
            
        } finally {
            em.close();
        }
    }
    
    public Company deleteCompany(int id)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Company c = em.find(Company.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return c;
        }
        finally
        {
            em.close();
        }
    }
}
