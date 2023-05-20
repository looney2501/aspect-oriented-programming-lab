package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;
import main.Main;
import domain.dtos.RaceDTO;
import domain.dtos.RaceDetailsDTO;
import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;


public class MainController extends Controller {

    @FXML
    private ComboBox<SwimmingDistances> distanceComboBox;
    @FXML
    private ComboBox<SwimmingStyles> styleComboBox;
    @FXML
    private TableView<RaceDTO> racesTableView;
    @FXML
    private TableColumn<RaceDTO, String> raceDistanceColumn;
    @FXML
    private TableColumn<RaceDTO, String> raceStyleColumn;
    @FXML
    private TableColumn<RaceDTO, String> raceSwimmersNoColumn;
    @FXML
    public final ObservableList<RaceDTO> raceDTOsModel = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<SwimmingDistances> distances = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<SwimmingStyles> styles = FXCollections.observableArrayList();
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField ageTextField;

    @FXML
    public void logoutButtonAction() throws IOException {
        Main.changeSceneToLogin();
    }

    @FXML
    public void raceSearchAction() throws IOException {
        SwimmingDistances swimmingDistance = distanceComboBox.getValue();
        SwimmingStyles swimmingStyle = styleComboBox.getValue();
        if (swimmingDistance == null || swimmingStyle == null) {
            MessageAlert.showErrorMessage(null, "Valori invalide!");
        }
        else {
            Main.changeSceneToRaceView(swimmingDistance, swimmingStyle);
        }
    }

    @FXML
    public void addSwimmerAction() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String age = ageTextField.getText();
        ObservableList<RaceDTO> selectedIndices = racesTableView.getSelectionModel().getSelectedItems();
        if (firstName.isEmpty()) {
            MessageAlert.showErrorMessage(null, "Introduceti prenume!");
        }
        else if (lastName.isEmpty()) {
            MessageAlert.showErrorMessage(null, "Introduceti nume!");
        }
        else if (age.isEmpty()) {
            MessageAlert.showErrorMessage(null, "Introduceti varsta!");
        }
        else if (selectedIndices.size() < 1) {
            MessageAlert.showErrorMessage(null, "Selectati cel putin o cursa!");
        }
        else {
            serviceAddSwimmer(firstName, lastName, age, selectedIndices);
        }
    }

    private void serviceAddSwimmer(String firstName, String lastName, String age, ObservableList<RaceDTO> selectedIndices) {
        List<RaceDetailsDTO> raceDetailsDTOs = selectedIndices.stream()
                .map(raceDTO -> new RaceDetailsDTO(raceDTO.getDistance(), raceDTO.getStyle()))
                .toList();
        service.addSwimmer(firstName, lastName, Integer.parseInt(age), raceDetailsDTOs);
        firstNameTextField.clear();
        lastNameTextField.clear();
        ageTextField.clear();
    }

    public void initialize() {
        initializeModels();
        initializeRacesTableView();
        initializeComboBoxes();
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        ageTextField.setTextFormatter(
                new TextFormatter<>(new IntegerStringConverter(), 0, integerFilter));
        ageTextField.clear();
    }

    private void initializeRacesTableView() {
        racesTableView.setItems(raceDTOsModel);
        racesTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        raceDistanceColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDistance().toString()));
        raceStyleColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStyle().toString()));
        raceSwimmersNoColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNoSwimmers().toString()));
    }

    private void initializeModels() {
        raceDTOsModel.setAll(service.findAllRacesDetails());
        distances.setAll(Arrays.asList(SwimmingDistances._50m, SwimmingDistances._200m, SwimmingDistances._800m, SwimmingDistances._1500m));
        styles.setAll(Arrays.asList(SwimmingStyles._FREESTYLE, SwimmingStyles._BACKSTROKE, SwimmingStyles._BUTTERFLY, SwimmingStyles._MIXED));
    }

    private void initializeComboBoxes() {
        distanceComboBox.setItems(distances);
        styleComboBox.setItems(styles);
    }
}
