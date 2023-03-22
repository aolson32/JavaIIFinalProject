/**
 * This is the controller class. This is where all of the functions for  
 * the project are assigned
 * 
 * @author Anna Olson
 */

package javaiifinalprojectforrealmaybe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MaybeActuallyFXMLController implements Initializable {
    
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonLoad;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonUpdate;
    @FXML
    private ComboBox comboBoxClass;
    @FXML
    private ComboBox comboBoxRace;
    @FXML
    private ListView listViewCharacters;
    @FXML
    private TextField textFieldChooseClass;
    @FXML
    private TextField textFieldChooseRace;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextArea textFieldCharExtra;

    private ApiBit rcApi;
    private ChangeListener<RaceAndClass> rcChangeListener;
    private ChangeListener<Character> charChangeListener;
    
    private ObservableList<Character> characterList = FXCollections.observableArrayList(Character.extractor);
    private Character selectedCharacter;
    
    /**
     * this section assigns comboBox, button, and list values
     * it also prepopulates some of the site
     * determines how some things function
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rcApi = new ApiBit();
        ArrayList<RaceAndClass> classes = rcApi.getClasses();
        ArrayList<RaceAndClass> races = rcApi.getRaces();
        ObservableList<RaceAndClass> observableClasses = FXCollections.observableArrayList(classes);
        ObservableList<RaceAndClass> observableRaces = FXCollections.observableArrayList(races);
        comboBoxClass.setItems(observableClasses);
        comboBoxRace.setItems(observableRaces);
        Callback<ListView<RaceAndClass>, ListCell<RaceAndClass>> factory = lv -> new ListCell<RaceAndClass>() {
            @Override
            protected void updateItem(RaceAndClass item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        comboBoxClass.setCellFactory(factory);
        comboBoxClass.setButtonCell(factory.call(null));
        comboBoxRace.setCellFactory(factory);
        comboBoxRace.setButtonCell(factory.call(null));
        
        comboBoxClass.getSelectionModel().selectedItemProperty().addListener(
            rcChangeListener = (observable, oldValue, newValue) -> {
                RaceAndClass classInfo = newValue;
                textFieldChooseClass.setText(classInfo.name);
               
            }
        );
        comboBoxRace.getSelectionModel().selectedItemProperty().addListener(
            rcChangeListener = (observable, oldValue, newValue) -> {
                RaceAndClass raceInfo = newValue;
                textFieldChooseRace.setText(raceInfo.name);
               
            }
        );       
        //pre-population of the listView
        characterList.add(new Character("Morgunn", "Strongtooth", "Dwarf", "Rogue", "Mute. Currently leveled up to level 13. Has daggers (lots) and currently has custody of an invisibility cloak. A clan member of the Royal Creat Clan Agrund who was born to the clan Grombrindel"));
        characterList.add(new Character("Barry B.", "Benson", "Elf", "Rogue", "Born a Halfling from Brandybeer and was later reincarnated as an Elf after dying to Mazarroth. He is responsible for reviving the Crystalith strain. He is a general menance to society"));
        characterList.add(new Character("Herb", "Berb", "Human", "Monk", "Old"));
        listViewCharacters.setItems(characterList);
        
        
        listViewCharacters.getSelectionModel().selectedItemProperty().addListener(
            charChangeListener = (observable, oldValue, newValue) -> {
                selectedCharacter = newValue;

                if(newValue != null){
                    textFieldFirstName.setText(selectedCharacter.getFirstName());
                    textFieldLastName.setText(selectedCharacter.getLastName());
                    textFieldChooseClass.setText(selectedCharacter.getCharClass());
                    textFieldChooseRace.setText(selectedCharacter.getCharRace());
                    textFieldCharExtra.setText(selectedCharacter.getCharExtra());
                }
                else
                {
                    textFieldFirstName.setText("");
                    textFieldLastName.setText("");
                    textFieldChooseClass.setText("");
                    textFieldChooseRace.setText("");
                    textFieldCharExtra.setText("");
                }
            }
        );
   
        buttonAdd.disableProperty().bind(
            textFieldFirstName.textProperty().isEmpty()
                    .or(textFieldLastName.textProperty().isEmpty()));
        //these buttons will not appear unless a character is selected
        buttonUpdate.disableProperty().bind(listViewCharacters.getSelectionModel().selectedItemProperty().isNull());
        buttonDelete.disableProperty().bind(listViewCharacters.getSelectionModel().selectedItemProperty().isNull());
        buttonSave.disableProperty().bind(listViewCharacters.getSelectionModel().selectedItemProperty().isNull());

    }    
    
    
    @FXML
    void chooseClass(ActionEvent event) {
        String s = comboBoxClass.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void chooseRace(ActionEvent event) {
        String t = comboBoxClass.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    private void addCharacter(ActionEvent event) 
    {
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String charClass = textFieldChooseClass.getText();
        String charRace = textFieldChooseRace.getText();
        String charExtra = textFieldCharExtra.getText();
        
        
        Character character = new Character (firstName, lastName, charClass, charRace, charExtra);
        
        characterList.add(character);
        
        listViewCharacters.getSelectionModel().select(character);
        
        //clears the textFields for the new character
        character.setFirstName("First");
        character.setLastName("Name");
        character.setCharClass("");
        character.setCharRace("");
        character.setCharExtra("");
    }


    @FXML //deletes character if it is selected
    private void deleteCharacter(ActionEvent event) {
        characterList.remove(selectedCharacter);
    }

    @FXML //loads in 1 character from file ("CharacterBook.txt")
    void loadCharacter(ActionEvent event) throws FileNotFoundException {
        characterList = loadFromFile();
    }
 
    @FXML //saves the character to the same file ("CharacterBook.txt") after automatically updating it
    void saveCharacter(ActionEvent event) {
        Character character = (Character) listViewCharacters.getSelectionModel().getSelectedItem();
        
        listViewCharacters.getSelectionModel().selectedItemProperty().removeListener(charChangeListener);
        
        updateCharacter(event);
        saveToFile(character);
        
        listViewCharacters.getSelectionModel().selectedItemProperty().addListener(charChangeListener);
    }

    @FXML //this saves any of the changes that have occured in the differnt fields/boxes/area
    private void updateCharacter(ActionEvent event)
    {
        Character character = (Character) listViewCharacters.getSelectionModel().getSelectedItem();
        
        listViewCharacters.getSelectionModel().selectedItemProperty().removeListener(charChangeListener);
        
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String chooseClass = textFieldChooseClass.getText();
        String chooseRace = textFieldChooseRace.getText();
        String charExtra = textFieldCharExtra.getText();
        
        character.setLastName(lastName);
        character.setFirstName(firstName);
        character.setCharClass(chooseClass);
        character.setCharRace(chooseRace);
        character.setCharExtra(charExtra);
        
        listViewCharacters.getSelectionModel().selectedItemProperty().addListener(charChangeListener);
    }
    
    private static final String FILE_NAME = "CharacterBook.txt";

    public static void saveToFile(Character characters){
        PrintWriter writer = null;
        Character character;
        
        try
        {
            File file = new File(FILE_NAME);
            writer = new PrintWriter(file);
            
            character = characters;
            writer.println(character.getFirstName() + "," + character.getLastName() + "," + character.getCharClass() + "," + character.getCharRace() + "," + character.getCharExtra());
            
            System.out.println("Your Character File has been saved");
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Error, file not found. " + exception.getMessage());
        }
        finally
        {
            writer.close();
        }
        System.out.println();  
    }
    
    public ObservableList<Character> loadFromFile() throws FileNotFoundException{
        //ArrayList<Character> characters = new ArrayList<>();
        Character character;
        String line;
        String[] tokens;
        String firstName;
        String lastName;
        String charClass;
        String charRace;
        String charExtra;
        
        try(Scanner fileInput = new Scanner(new File(FILE_NAME)))
        {
            while(fileInput.hasNext())
            {
                line = fileInput.nextLine();
                tokens = line.split(",");
                character = new Character();
                
                firstName = tokens[0];
                lastName = tokens[1];
                charClass = tokens[2];
                charRace = tokens[3];
                charExtra = tokens[4];
                
                character.setFirstName(firstName);
                character.setLastName(lastName);
                character.setCharClass(charClass);
                character.setCharRace(charRace);
                character.setCharExtra(charExtra);
                
                characterList.add(character);
            }
            System.out.println("Your Character Book has been loaded into the system");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error, " + FILE_NAME + " could not be found.");
            System.out.println("More details: " + e.getMessage());
        }
        System.out.println();
        
        return characterList;
    }
}
