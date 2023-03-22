/**
 * This is where the second part of the api is processed.
 * The api used in this project has an intro section and array section, and this is where the array section is dealt with.
 * This is used later on to construct both the Race and Class inputs from the api (as they are both set up the same)
 * 
 * @author Anna Olson
 */
package javaiifinalprojectforrealmaybe;

public class RaceAndClass implements Comparable<RaceAndClass>
{
    public String index;
    public String name;
    public String url;
    
    /**
     * constructor for the RaceAndClass variables
     */
    public RaceAndClass(){
        index = "";
        name = "";
        url = "";
    }
    /**
     * constructor that takes in parameters
     * @param inIndex
     * @param inName
     * @param inUrl 
     */
    public RaceAndClass(String inIndex, String inName, String inUrl){
        index = inIndex;
        name = inName;
        url = inUrl;
    }
    
    /**
     * this is where the index is called
     * @return 
     */
    public String getIndex() {
        return index;
    }
    /**
     * this is where the name is called
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * this is where the url is called
     * @return 
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RaceAndClass{" + "index=" + index + ", name=" + name + ", url=" + url + '}';
    }
    
    @Override
    public int compareTo(RaceAndClass other)
    {
        return name.compareTo(other.name);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual;
        
        if (this == obj)
        {
            isEqual = true;
        }
        else if (obj == null)
        {
            isEqual = false;
        }
        else if (getClass() != obj.getClass())
        {
            isEqual = false;
        }
        else
        {
            RaceAndClass other = (RaceAndClass) obj;
            isEqual = name.equals(other.name);
        }
        
        return isEqual;
    }
    
}
