
/**
 * This class represent a country
 *
 * @Eithan Alexander Lavi
 * @19/1/19
 */
public class Country
{
    final int DEFAULT_VALUE = 0;
    private String _countryName;
    private CityNode _head;

    /**
     * Constructor for objects of class Country
     * run time is O(1) since it return one element
     * complexity is O(2) since there is two element in the memory which is like O(1)
     */
    public Country(String countryName)
    {
        _countryName = countryName;
        _head = null;
    }

    /**
     * Return the country name
     * run time is O(1) since it return one element
     * complexity is O(1) since there is one element in the memory
     */
    public String getCountryName()
    {
        return _countryName;   
    }

    /**
     * Return the number of cities in the country
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since nothing is created in memory that would stay
     */
    public int getNumOfCities()
    {
        int numOfCities = DEFAULT_VALUE;
        CityNode ptr = _head;

        if(ptr == null)
        {
            return numOfCities;  
        }
        else
        {
            while(ptr != null)
            {
                numOfCities++;
                ptr = ptr.getNext();
            }
        }
        return numOfCities;
    }

    /**
     * adds a city to the list if it does not already exist
     * run time is O(n) since it would go through the list only one time at the most
     * complexity O(1) since one object is created in the memory
     */
    public boolean addCity(String cityName, double xCenter, double yCenter,double xStation, double yStation,
    int numOfResidents,int noOfNeighborhoods)
    {
        CityNode hp = _head;
        City city = new City(cityName,xCenter,yCenter,xStation,yStation,numOfResidents,noOfNeighborhoods);
        //adding a new city, the constructor would make the pointer of that city null
        CityNode cn;   
        
        //checks if the list is empty, if it is than add the city to the head of the list     
        if(_head == null)
        {
            cn = new CityNode(city);
            _head = cn;
            
            return true;
        }
        //while there are pointers to other objects in the list keep advancing until you get null
        boolean exist = cityExist(cityName);
        if(exist)
        {
         return false;   
        }
    
        //setting the next pointer to the last city added
        
        CityNode temp = _head;
        cn = new CityNode(city,temp);
        _head = cn;      
        cn.setNext(temp);
        
        return true;
    }

    /**
     * Checks if the city has already been added to the country according to it's name
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since nothing is created in memory that would stay
     */
    private boolean cityExist(String cityName)
    {
        CityNode ptr = _head;
        boolean doesExist = false;

        if(_head == null)
        {
            return doesExist;
        }
        else
        {
            while(ptr != null)
            {
                if(cityName.equals(ptr.getCity().getCityName()))
                {
                    doesExist = true;   
                    return doesExist;   
                }
                ptr = ptr.getNext();  
            }
            return doesExist;
        }
    }

    /**
     * Calculate the number of residence in the country
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since nothing is created in memory that would stay
     */
    public long getNumOfResidents()
    {
        long totalReseidentsNum = (long)DEFAULT_VALUE;
        CityNode ptr = _head;
        if(ptr != null)
        {
            while(ptr != null)
            {
                totalReseidentsNum = totalReseidentsNum + ptr.getCity().getNumOfResidents();
                ptr = ptr.getNext();
            }
            return totalReseidentsNum;
        }     
        return totalReseidentsNum;
    }

    /**
     * Returns the pointer of the city according to its name
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since nothing is created in memory that would stay
     */
    private CityNode ptrByName(String cityName)
    {
        CityNode ptr = _head;

        if(ptr!= null)
        {
            while(ptr!= null)
            {
                if(cityName.equals(ptr.getCity().getCityName()))
                {
                    return ptr;                    
                }
                ptr = ptr.getNext();
            }
        }
        return null;
    }

    /**
     * Return the longest distance between two city centers of that country
     * run time is O(n^2) since: one loop go through the list while another loop inside does the same thing (only be n-1),
     * so it would be O(n) * O(n) = O(n^2)
     * complexity O(1) since nothing is created in memory that would stay
     */
    public double longestDistance()
    {
        double longestDistance = DEFAULT_VALUE;
        double distanceTemp = DEFAULT_VALUE;
        CityNode ptr = _head;
        CityNode tempDistancePtr;
        int listSize = getNumOfCities();

        if(listSize < 2)
        {
            return DEFAULT_VALUE;   
        }

        for(int i = 0; i < listSize; i++)
        {
            tempDistancePtr = ptr;
            while(tempDistancePtr.getNext() != null)

            {
                tempDistancePtr = tempDistancePtr.getNext();
                distanceTemp = ptr.getCity().getCityCenter().distance(tempDistancePtr.getCity().getCityCenter());
                if(distanceTemp > longestDistance)
                {
                    longestDistance = distanceTemp;
                }
            }
            ptr = ptr.getNext();
        }

        return longestDistance;
    }

    /**
     * Return a String represntaion of all cities north of the city that was sent to the method
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since nothing is created in memory that would stay
     */
    public String citiesNorthOf(String cityName)
    {
        String northCitiesDetails = "";
        boolean northCitiesExist = false;
        CityNode ptr = ptrByName(cityName);
        CityNode ptrCompare = _head;
        Point northOfThisCityCenter = ptr.getCity().getCityCenter();

        if(ptr == null)
        {
            return "There is no city with the name " + cityName;    
        }

        while(ptrCompare != null)
        {
            Point otherCityCenterIsNorth = ptrCompare.getCity().getCityCenter();
            
            
            if(otherCityCenterIsNorth.isAbove(northOfThisCityCenter))
            {
                if(!northCitiesExist)
                {
                    northCitiesDetails = northCitiesDetails + "The cities north of "+ cityName +" are: \n";
                    northCitiesExist = true;
                }
                
                northCitiesDetails =  northCitiesDetails + ptrCompare.getCity().toString() + "\n";   
            }  
            ptrCompare = ptrCompare.getNext();
        }

        if(northCitiesDetails.equals(""))
        {
            return "There are no cities north of" + cityName;
        }

        return northCitiesDetails;
    }

    /**
     * Return the most south city in the country
     * run time is O(n) since it would go through the list only one time,
     * complexity O(1) since it return only one element and does not create anything new
     */
    public City southernmostCity()
    {
        CityNode mostSouth = _head;
        
        if(mostSouth == null)
        {
            return null;
        }
        
        CityNode southCompare = mostSouth.getNext(); 
        
        if(southCompare == null)
        {
         return    mostSouth.getCity();
        }
        
        while(southCompare != null)
        {
            if(southCompare.getCity().getCityCenter().isUnder(mostSouth.getCity().getCityCenter()))
            {
                mostSouth = southCompare;  
            }
            southCompare = southCompare.getNext();
        }

        City southCity = new City(mostSouth.getCity());
        return southCity;
    }
    
    /**
     * Return a copy of all cities in that country
     * run time is O(n) since it would go through the list only one time,
     * complexity O(n) since it would create the exact amount of cities in the origial country
     */
    public Country getCities()
    {
        Country countryCopy = new Country(_countryName);
        CityNode tempCopy = _head, tempN = null ,c = null;

        if(tempCopy == null)
        {
            return countryCopy;
        }

        for(CityNode head = _head; head != null; head = head.getNext())
        {
            c = new CityNode(tempCopy.getCity(),tempN);
            tempN = c;
            tempCopy = tempCopy.getNext();
        }

        countryCopy._head = c;
        return countryCopy; 
    }
    /**
     * Creates a new city using two existing cities. The smaller city, residents wise, would be deleted and 
     * in the bigger city stead would be the unified city and the pointer would be directed to that city.
     * run time is O(n) since it would go through the list only one time using "removeFromList" ,
     * complexity O(1) becuse there is one new city created in memory instead of the bigger one.
     */
    public City unifyCities(String city1, String city2)
    {
        CityNode uniCity1 = ptrByName(city1);
        CityNode uniCity2 = ptrByName(city2);
        CityNode moreResidents;
        CityNode lessResidents;

        //new name
        String unifiedCityName = city1 + "-" + city2;

        //new city center
        Point uniCityCenter1 = uniCity1.getCity().getCityCenter();
        Point uniCityCenter2 = uniCity2.getCity().getCityCenter();

        double newCityX1 = uniCityCenter1.getX();
        double newCityX2 = uniCityCenter2.getX();
        double newCityY1 = uniCityCenter1.getY();
        double newCityY2 = uniCityCenter2.getY();
        Point CityCenter = new Point((newCityX1+newCityX2)/2.0,(newCityY1+newCityY2)/2.0);

        //new central station
        Point uniCentralStation1 = uniCity1.getCity().getCentralStation();
        Point uniCentralStation2 = uniCity2.getCity().getCentralStation();

        Point newCentralStation;

        if(uniCentralStation1.isLeft(uniCentralStation2))
        {
            newCentralStation = uniCentralStation1;
        }
        else
        {
            newCentralStation = uniCentralStation2;
        }

        //number of residents
        long numOfResidentsUni = uniCity1.getCity().getNumOfResidents() + uniCity2.getCity().getNumOfResidents();
        //number of neighbithoods
        int noOfNeighborhoodsUni = uniCity1.getCity().getNoOfNeighborhoods() + uniCity2.getCity().getNoOfNeighborhoods();

        if(uniCity1.getCity().getNumOfResidents() >= uniCity2.getCity().getNumOfResidents())
        {
            moreResidents = uniCity1;
            lessResidents = uniCity2;
        }
        else
        {
            moreResidents = uniCity2;
            lessResidents = uniCity1;
        }

        removeFromList(lessResidents);
        City unifiedCity = new City(unifiedCityName,CityCenter.getX(),CityCenter.getY(),
                newCentralStation.getX(),newCentralStation.getY(),numOfResidentsUni,noOfNeighborhoodsUni);
        CityNode unifiedCityNode = new CityNode(unifiedCity,moreResidents.getNext());
            
        moreResidents.setNext(unifiedCityNode);
        removeFromList(moreResidents);
        return unifiedCity;
    }
    
    /**
     * Removing a pointer to the smaller city when we want to unify two cities.
     * run time is O(n) since it would go through the list only one time at most,
     * complexity O(1) since removing one pointer from memory
     */
    private void removeFromList(CityNode smaller)
    {
        CityNode behindeDeleted = _head;
        
        if(_head != null)
        {
            
            if (behindeDeleted == smaller)
            {
                _head = _head.getNext();
                return;
            }
            
            while(behindeDeleted.getNext() != smaller)
            {
                behindeDeleted = behindeDeleted.getNext();
            }
            
            behindeDeleted.setNext(behindeDeleted.getNext().getNext());
        }       
    }
    
    /**
     * String represantation of all the cities in the country
     * run time is O(n) since it would go through the list only one time,
     * complexity O(n) since it cretes the "allCitiesInTheCountrie" string and return the result which are all the cities
     */
    public String toString()
    {
        String allCitiesInTheCountrie = "";
        
        CityNode start = _head;

        if(start == null)
        {
            return "There are no cities in this country.";    
        }

        while(start != null)
        {
            allCitiesInTheCountrie = allCitiesInTheCountrie + start.getCity() +"\n \n";
            start = start.getNext();
        }

        return "Cities of " + getCountryName() + ": \n"+ allCitiesInTheCountrie;
    }
}  

  