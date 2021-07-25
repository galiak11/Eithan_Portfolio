
/**
 * Represents a pointer to a city in a country
 *
 * @Eithan Alexander Lavi
 * @19/1/19
 */
public class CityNode
{
    private City _city;
    private CityNode _next;

    /**
     * Constructor that set the next pointer to null
     * run time is O(1) since it return one element
     * complexity is O(2) since there is two element in the memory which is like O(1)
     */
    public CityNode (City c)
    {
        _city = c;
        _next = null;
    }

    /**
     * Constructor that set the next pointer to a certin city (or null if it's the last)
     * run time is O(1) since it return one element
     * complexity is O(2) since there is two element in the memory which is like O(1)
     */
    public CityNode (City c, CityNode n)
    {
        _city = new City(c);
        _next = n;
    }

    /**
     * creates a pointer to a city
     * run time is O(1) since it return one element
     * complexity is O(1) since there is one element in the memory
     */
    public CityNode (CityNode c)
    
    {
        _next = c;
    }
    
    /**
     * Return the city that is calles via a pointer
     * run time is O(1) since it return one element
     * complexity is O(1) since there is one element in the memory
     */
    public City getCity()
    {
        return _city;   
    }
    
    /**
     * Return the next pointer
     * run time is O(1) since it return one element
     * complexity is O(1) since there is one element in the memory
     */
    public CityNode getNext()
    {
        return _next;   
    }
    
    /**
     * Create a new city 
     * run time is O(1) since it return one element
     * complexity is O(1) since there is one element in the memory
     */
    public void setCity(City c)
    {
        _city = new City (c);  
    }
    
    /**
     * seting the next city the pointer would be at
     */
    public void setNext(CityNode next)
    {
        _next = next;   
    }

}
