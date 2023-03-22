/**
 * This is where the main character features are set up.
 * Properties and Bindings are used on the character features.
 * 
 * @author Anna Olson
 */
package javaiifinalprojectforrealmaybe;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 * Character Class - sets up the Character that is used in the 
 * rest of the program
 */
public class Character
{
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty charClass;
    private StringProperty charRace;
    private StringProperty charExtra;
    
    /**
     * Constructor that sets the variables to SimpleStringProperties
     */
    public Character(){
        firstName = new SimpleStringProperty(this, "firstName", "");
        lastName = new SimpleStringProperty(this, "lastName", "");
        charClass = new SimpleStringProperty(this, "charClass", "");
        charRace = new SimpleStringProperty(this, "charRace", "");
        charExtra = new SimpleStringProperty(this, "charExtra", "");
       
    }
    /**
     * parameter constructor - takes in variables as parameters
     * 
     * @param inFirstName each character has a first name
     * @param inLastName each character has a last name
     * @param inCharClass each character has a class
     * @param inCharRace each character has a race
     * @param inCharExtra each character can have extra information
     */
    public Character(String inFirstName, String inLastName, String inCharClass, String inCharRace, String inCharExtra){
        firstName = new SimpleStringProperty(this, "firstName", inFirstName);
        lastName = new SimpleStringProperty(this, "lastName", inLastName);
        charClass = new SimpleStringProperty(this, "charClass", inCharClass);
        charRace = new SimpleStringProperty(this, "charRace", inCharRace);
        charExtra = new SimpleStringProperty(this, "charExtra", inCharExtra);
       
    }

    /**
     * This part sets up all of the get methods used for this program
     * (All done together for organizational purposes)
     * @getMethods 
     */
    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public String getCharClass() {
        return charClass.get();
    }
    public String getCharRace() {
        return charRace.get();
    }
    public String getCharExtra() {
        return charExtra.get();
    }
    

    /**
     * This section is all the set Methods used in the program
     * 
     * @param inFirstName 
     * @param inLastName
     * @param inCharClass
     * @param inCharRace
     * @param inCharExtra
     */
    public void setFirstName(String inFirstName) {
        firstName.set(inFirstName);
    }
    public void setLastName(String inLastName) {
        lastName.set(inLastName);
    }
    public void setCharClass(String inCharClass) {
        charClass.set(inCharClass);
    }
    public void setCharRace(String inCharRace) {
        charRace.set(inCharRace);
    }
    public void setCharExtra(String inCharExtra) {
        charExtra.set(inCharExtra);
    }

    
    /**
     * All of the property bindings are in this section
     *  
     */
    public StringProperty firstNameProperty()
    {
        return firstName;
    }
    public StringProperty lastNameProperty()
    {
        return lastName;
    }
    public StringProperty charClassProperty()
    {
        return charClass;
    }
    public StringProperty charRaceProperty()
    {
        return charRace;
    }
    public StringProperty charExtraProperty()
    {
        return charExtra;
    }

    /**
     * The toString, it returns just the first and last name values
     * @return 
     */
    @Override
    public String toString()
    {
        return firstName.getValue() + " " + lastName.getValue();
    }
    

    /**
     * This compares the different characters to determine if a character is indeed a character
     * @param obj
     * @return 
     */
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
            Character other = (Character) obj;
            
            if (getFirstName().equals(other.getFirstName())
                    && getLastName().equals(other.getLastName())
                    && getCharClass().equals(other.getCharClass())
                    && getCharRace().equals(other.getCharRace())
                    && getCharExtra().equals(other.getCharExtra()))
            {
                isEqual = true;
            }
            else
            {
                isEqual = false;
            }
        }
        
        return isEqual;
    }
    
    /**
     * This is for extracting for and observableList
     */
    public static Callback<Character, Observable[]> extractor = p -> new Observable[]
        {p.firstNameProperty(), p.lastNameProperty()};
    
}
