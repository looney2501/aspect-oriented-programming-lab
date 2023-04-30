package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Main;
import domain.dtos.SwimmerDTO;
import domain.enums.SwimmingDistances;
import domain.enums.SwimmingStyles;

import java.io.IOException;

public class RaceViewController extends Controller {

    @FXML
    private TableView<SwimmerDTO> raceSwimmersTableView;
    @FXML
    private TableColumn<SwimmerDTO, String> firstNameColumn;
    @FXML
    private TableColumn<SwimmerDTO, String> lastNameColumn;
    @FXML
    private TableColumn<SwimmerDTO, String> ageColumn;
    @FXML
    private TableColumn<SwimmerDTO, String> racesEnrolledColumn;
    @FXML
    private final ObservableList<SwimmerDTO> swimmerDTOsModel = FXCollections.observableArrayList();
    private final SwimmingDistances swimmingDistance;
    private final SwimmingStyles swimmingStyle;

    public RaceViewController(SwimmingDistances swimmingDistance, SwimmingStyles swimmingStyle) {
        this.swimmingDistance = swimmingDistance;
        this.swimmingStyle = swimmingStyle;
    }

    @FXML
    public void goBackAction() throws IOException {
        Main.changeSceneToMainView();
    }

    public void initialize() {
        initializeModels();
        initializeTableView();
    }

    private void initializeModels() {
        swimmerDTOsModel.setAll(service.findAllSwimmersDetailsForRace(this.swimmingDistance, this.swimmingStyle));
    }

    private void initializeTableView() {
        raceSwimmersTableView.setItems(swimmerDTOsModel);
        firstNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSwimmer().getFirstName()));
        lastNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSwimmer().getLastName()));
        ageColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSwimmer().getAge().toString()));
        racesEnrolledColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRaces()));
    }
}
