package GUI;

import AbstrTable.eTypProhl;
import AgendaKraj.AgendaKraj;
import Obec.Obec;
import Obec.enumKraje;
import java.io.IOException;
import java.util.Iterator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgAgenda extends Application {

    private AgendaKraj agendaKraj = new AgendaKraj();
    private TextArea outputArea;
    private TextField searchField, fileField, nameField, pscField, pocetMuzuField, pocetZenField;
    private ComboBox<enumKraje> krajComboBox;
    private BorderPane bp;
    private ListView<Obec> obciLV;
    ObservableList<eTypProhl> list = FXCollections.observableArrayList(eTypProhl.values());
    ComboBox typItrCB = new ComboBox(list);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agenda Kraj");

        createList();

        // Setup UI elements
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(300);

        searchField = new TextField();
        searchField.setMaxSize(180, 40);
        fileField = new TextField();
        fileField.setMaxSize(180, 40);
        nameField = new TextField();
        nameField.setMaxSize(180, 40);
        pscField = new TextField();
        pscField.setMaxSize(180, 40);
        pocetMuzuField = new TextField();
        pocetMuzuField.setMaxSize(180, 40);
        pocetZenField = new TextField();
        pocetZenField.setMaxSize(180, 40);

        krajComboBox = new ComboBox<>();
        krajComboBox.setMaxSize(180, 40);
        krajComboBox.getItems().addAll(enumKraje.values());

        Button importButton = new Button("Importovat Data");
        importButton.setMaxSize(180, 40);
        importButton.setOnAction(e -> importData());

        Button findButton = new Button("Najít Obec");
        findButton.setMaxSize(180, 40);
        findButton.setOnAction(e -> findObec());

        Button addButton = new Button("Přidat Obec");
        addButton.setMaxSize(180, 40);
        addButton.setOnAction(e -> addObec());

        Button removeButton = new Button("Odebrat Obec");
        removeButton.setMaxSize(180, 40);
        removeButton.setOnAction(e -> removeObec());

        Button buildTreeButton = new Button("Vybudovat Vyvážený BVS");
        buildTreeButton.setMaxSize(180, 40);
        buildTreeButton.setOnAction(e -> buildTree());

        Button generateButton = new Button("Generovat Obec");
        generateButton.setMaxSize(180, 40);
        generateButton.setOnAction(e -> generateObec());

        Button displayAllButton = new Button("Zobrazit Všechny Obce");
        displayAllButton.setMaxSize(180, 40);
        displayAllButton.setOnAction(e -> displayAllObce());

        Button zrusButton = new Button("Zrus");
        zrusButton.setMaxSize(180, 40);
        zrusButton.setOnAction(e -> zrusData());

        Label typItrLbl = new Label("Typ iteratora");
//
//        typItrCB.setPrefSize(100, 30);
//        typItrCB.setOnAction(e -> displayAllObce());

        // Layout
        bp = new BorderPane();
        bp.setPadding(new Insets(10));

        BorderPane controls = new BorderPane();
        VBox leftControls = new VBox(10);
        leftControls.setPadding(new Insets(0, 10, 0, 10));
        VBox rightControls = new VBox(10);
        rightControls.setPadding(new Insets(0, 10, 0, 10));
        controls.setMaxWidth(230);
        controls.setPadding(new Insets(10, 10, 0, 10));
        controls.setLeft(leftControls);
        controls.setRight(rightControls);
        controls.setBottom(outputArea);

        leftControls.getChildren().addAll(
                importButton,
                new Label("Název obce k hledání:"), searchField, findButton,
                buildTreeButton, generateButton, zrusButton, displayAllButton, typItrLbl, typItrCB
        );

        rightControls.getChildren().addAll(
                new Label("Název:"), nameField,
                new Label("Kraj:"), krajComboBox,
                new Label("PSC:"), pscField,
                new Label("Počet Mužů:"), pocetMuzuField,
                new Label("Počet Žen:"), pocetZenField,
                addButton, removeButton
        );

        bp.setRight(controls);
        bp.setCenter(obciLV);

        primaryStage.setScene(new Scene(bp, 1200, 722));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void createList() {
        ObservableList<Obec> listData = FXCollections.observableArrayList();
        obciLV = new ListView<>(listData);
        obciLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        obciLV.setPrefSize(800, 800);
    }

    private void importData() {
        try {
            String fileName = "kraje.csv";
            int count = agendaKraj.importData(fileName);
            outputArea.setText("Načteno " + count + " obcí z " + fileName);
        } catch (IOException e) {
            outputArea.setText("Chyba při načítání souboru: " + e.getMessage());
        }
    }

    private void findObec() {
        String key = searchField.getText().trim();
        Obec obec = agendaKraj.najdi(key);
        if (obec != null) {
            outputArea.setText("Nalezena obec: " + obec.toString());
        } else {
            outputArea.setText("Obec nenalezena.");
        }
    }

    private void addObec() {
        try {
            String name = nameField.getText().trim();
            enumKraje kraj = krajComboBox.getValue();
            if (pscField.getText().isEmpty() || pocetMuzuField.getText().isEmpty() || pocetZenField.getText().isEmpty()) {
                outputArea.setText("Prosím vyplňte všechna pole.");
                return;
            }
            int psc = Integer.parseInt(pscField.getText().trim());
            int pocetMuzu = Integer.parseInt(pocetMuzuField.getText().trim());
            int pocetZen = Integer.parseInt(pocetZenField.getText().trim());

            Obec novaObec = new Obec(name, kraj, psc, pocetMuzu, pocetZen);
            agendaKraj.vloz(novaObec);
            outputArea.setText("Obec přidána: " + novaObec.toString());
        } catch (NumberFormatException e) {
            outputArea.setText("Chyba při převodu čísel: " + e.getMessage());
        } catch (Exception e) {
            outputArea.setText("Chyba při přidávání obce: " + e.getMessage());
        }
    }

    private void removeObec() {
        String key = searchField.getText().trim();
        Obec removed = agendaKraj.odeber(key);
        if (removed != null) {
            outputArea.setText("Odebrána obec: " + removed.toString());
        } else {
            outputArea.setText("Obec k odebrání nenalezena.");
        }
    }

    private void buildTree() {
        try {
            agendaKraj.Vybuduj();
            outputArea.setText("BVS byl vybudován a vyvážen.");
            displayAllObce();
        } catch (Exception e) {
            outputArea.setText("Chyba při budování BVS: " + e.getMessage());
        }
    }

    private void generateObec() {
        Obec novaObec = agendaKraj.Generuj();
        agendaKraj.vloz(novaObec);
        outputArea.setText("Vygenerována a přidána obec: " + novaObec.toString());
    }

    private void displayAllObce() {
        eTypProhl typProhl = (eTypProhl) typItrCB.getValue();
        Iterator<Obec> itr = agendaKraj.VytvorIterator(eTypProhl.HLOUBKA);
        obciLV.getItems().clear();
        if (typProhl == eTypProhl.SIRKA) {
            itr = agendaKraj.VytvorIterator(eTypProhl.SIRKA);
        }
        while (itr.hasNext()) {
            obciLV.getItems().add(itr.next());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void zrusData() {
        agendaKraj.zrus();
        displayAllObce();
    }
}
