
/**
 * This class represents a city
 *
 * @Eithan Alexander Lavi
 * @Date 24/11/18
 */
public class City
{
    final int MIN_RESIDENTS = 0;
    final int MIN_NEIGHBORHOODS = 1;
    final int NEW_CITY_NEIGHBORHOODS_START_VALUE = 1;
    final int MIX_X_VALUE = 0;
    final int MIV_Y_VALUE = 0;

    private String _cityName;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _noOfNeighborhoods;

    /**
     * Constructor for objects of class City
     */
    public City(String cityName, double cityCenterX, double cityCenterY, double centralStationX,
    double centralStationY, long numOfResidents, int noOfNeighborhoods)
    {
        _cityName = cityName;
        _cityCenter = new Point(cityCenterX, cityCenterY);
        _centralStation = new Point(centralStationX, centralStationY);

        if (numOfResidents < MIN_RESIDENTS)
        {
            _numOfResidents = MIN_RESIDENTS;
        }
        else
        {
            _numOfResidents = numOfResidents;
        }

        if (noOfNeighborhoods < MIN_NEIGHBORHOODS )
        {
            noOfNeighborhoods = MIN_NEIGHBORHOODS;
        }
        else
        {
            _noOfNeighborhoods = noOfNeighborhoods;
        }
    }

    /**
     * Copy constractor
     */
    public City(City other)
    {
        _cityName = other._cityName;
        _cityCenter = other._cityCenter;
        _centralStation = other._centralStation;
        _numOfResidents = other._numOfResidents;
        _noOfNeighborhoods = other._noOfNeighborhoods;
    }

    public String getCityName()
    {
        return _cityName;       
    }

    public Point getCityCenter()
    {
        return _cityCenter;   
    }

    public Point getCentralStation()
    {
        return _centralStation;
    }

    public long getNumOfResidents()
    {
        return _numOfResidents;  
    }

    public int getNoOfNeighborhoods()
    {
        return _noOfNeighborhoods;
    }

    void setCityName(String cityName)
    {
        _cityName = cityName;  
    }

    void setCityCenter(Point cityCenter)
    {
        double x = cityCenter.getX();
        double y = cityCenter.getY();
        
        _cityCenter = new Point(x,y);
    }

    void setCentralStation(Point centralStation)
    {
        double x = centralStation.getX();
        double y = centralStation.getY();
        
        _centralStation = new Point(x,y);
    }

    /**
     * Sets the number of residents, can't be below MIN_RESIDENTS
     */
    void setNumOfResidents(long numOfResidents)
    {
        if (numOfResidents < MIN_RESIDENTS)
        {
            _numOfResidents = MIN_RESIDENTS;
        }
        else
        {
            _numOfResidents = numOfResidents;
        }
    }

    /**
     * Sets the number of neighborhoods, can't be below MIN_NEIGHBORHOODS
     */
    void setNoOfNeighborhoods(int noOfNeighborhoods)
    {
        if (noOfNeighborhoods < MIN_NEIGHBORHOODS )
        {
            noOfNeighborhoods = MIN_NEIGHBORHOODS;
        }
        else
        {
            _noOfNeighborhoods = noOfNeighborhoods;
        }
    }

    /**
     * String representation
     */
    public String toString()
    {
        return "City name: " + _cityName + "\n"
        + "City Center: " + _cityCenter + "\n"
        + "Central Station: " + _centralStation + "\n"
        + "Number of Residents: " + _numOfResidents + "\n"
        + "Number of Neighborhoods: " + _noOfNeighborhoods + "\n";
    }

    /**
     * Add residents or decrease from current residents in the city
     */
    boolean addResidents(long residentsUpdate)
    {
        long currntResidents = getNumOfResidents();

        if(currntResidents + residentsUpdate >= MIN_RESIDENTS)
        {
            setNumOfResidents(currntResidents + residentsUpdate); 
            return true;
        }
        else
        {
            setNumOfResidents(0);
            return false;
        }
    }

    /**
     * Move central station location
     */
    void moveCentralStation(double deltaX, double deltaY)
    {
        _centralStation.move(deltaX, deltaY);
    }

    /**
     * Calcultes the distance between the city center and the central station
     */
    public double distanceBetweenCenterAndStation()
    {   
        return _cityCenter.distance(_centralStation);
    }

    /**
     * Create a new city using another city's location to set values of city center
     * and central station 
     */
    City newCity(String newCityName, double dX, double dY)
    {
        double xCityCenter = _cityCenter.getX() + dX;
        double yCityCenter = _cityCenter.getY() + dY;
        double xCentral = _centralStation.getX() + dX;
        double yCentral = _centralStation.getY() + dY;

        return new City(newCityName, xCityCenter, yCityCenter, xCentral, yCentral, MIN_RESIDENTS,
            NEW_CITY_NEIGHBORHOODS_START_VALUE);
    }
}
