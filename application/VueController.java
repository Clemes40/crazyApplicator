package application;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class VueController {
	
		@FXML
		private ImageView veloCourse;
		@FXML
		private ImageView veloVille;
		@FXML
		private Button commanderBtn;
		@FXML
		private ComboBox marque;
		@FXML
		private RadioButton oui;
		@FXML
		private RadioButton non;
		@FXML
		private TextField perso;
		@FXML
		private ImageView veloImageView;
	    @FXML
	    private ComboBox<Couleur> comboBoxCouleur;
	    @FXML
	    private ComboBox<Taille> comboBoxTaille;
	    @FXML
	    private ComboBox<Marque> comboBoxMarque;
	    @FXML
	    private ToggleGroup personnalisationGroup;
	    @FXML
	    private Label labelPrix;
	    private Velo veloActuel;
	    @FXML
	    public void initialize() {
	    	if (oui != null && non != null && perso != null) {
	    	    oui.setToggleGroup(personnalisationGroup);
	    	    non.setToggleGroup(personnalisationGroup);
	            perso.setDisable(true);
	            
	            oui.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
	                perso.setDisable(!isNowSelected);
	                if (isNowSelected) {
	                    
	                }
	                mettreAJourVeloEtPrix();
	            });

	            non.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
	                if (isNowSelected) {
	                    perso.setDisable(true);
	                }
	                mettreAJourVeloEtPrix();
	            });
	        }
	        
	        if (comboBoxCouleur != null) {
	            setupComboBoxesCouleur();
	        }
	        if (comboBoxTaille != null) {
	        	setupComboBoxesTaille();
	        }
	        if (comboBoxMarque != null) {
	        	setupComboBoxesMarque();
	        }
	    }
	    
	    @FXML
	    private void handleCommande(ActionEvent event) {
	        if (isCommandeValide()) {
	            afficherPopupConfirmation();
	        } else {
	            shakeNode(commanderBtn);
	        }
	    }
		
	    public void SwitchSceneCourse(MouseEvent e) throws IOException {
	        Velo velo = new Velo("Course", new Couleur("Default", 0.0f, "#ffffff"), new Marque("Default Marque",0.0f), "lienPhotoDefault", 200.0f, new Personnalisation("None", 0.0f), new Taille("Medium", 0.0f), new Image(getClass().getResourceAsStream("veloCourse.png")));
	        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
	        switchSceneWithFadeTransition("vue2.fxml", stage, velo);
	    }

	    public void SwitchSceneVille(MouseEvent e) throws IOException {
	        Velo velo = new Velo("Ville", new Couleur("Default", 0.0f,"#ffffff"), new Marque("Default Marque",0.0f), "lienPhotoDefault", 150.0f, new Personnalisation("None", 0.0f), new Taille("Medium", 0.0f), new Image(getClass().getResourceAsStream("veloVille.png")));
	        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
	        switchSceneWithFadeTransition("vue2.fxml", stage, velo);
	    }
	
		
	    public void initData(Velo velo) {
	        this.veloImageView.setImage(velo.getImage());
	        this.veloActuel = velo;
	        mettreAJourVeloEtPrix();
	    }
	    
	    public void setupComboBoxesCouleur() {
	        ObservableList<Couleur> listeDeCouleurs = FXCollections.observableArrayList(
	            new Couleur("Rouge", 10.0f, "#FF0000"),
	            new Couleur("Vert", 20.0f, "#00FF00"),
	            new Couleur("Bleu", 30.0f, "#0000FF")
	        );

	        comboBoxCouleur.setItems(listeDeCouleurs);
	        comboBoxCouleur.valueProperty().addListener((obs, oldVal, newVal) -> mettreAJourVeloEtPrix());

	        comboBoxCouleur.setCellFactory(listView -> new ListCell<Couleur>() {
	            @Override
	            protected void updateItem(Couleur couleur, boolean empty) {
	                super.updateItem(couleur, empty);
	                if (empty || couleur == null) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                    setText(couleur.getNom());
	                    Circle circle = new Circle(5, Color.web(couleur.getCodeCouleur()));
	                    setGraphic(circle);
	                }
	            }
	        });

	        comboBoxCouleur.setButtonCell(new ListCell<Couleur>() {
	            @Override
	            protected void updateItem(Couleur couleur, boolean empty) {
	                super.updateItem(couleur, empty);
	                if (empty || couleur == null) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                    setText(couleur.getNom());
	                    Circle circle = new Circle(5, Color.web(couleur.getCodeCouleur()));
	                    setGraphic(circle);
	                }
	            }
	        });
	    }

	   
	    public void setupComboBoxesTaille() {
	        ObservableList<Taille> listeDeTaille = FXCollections.observableArrayList(
	            new Taille("S", 10.0f),
	            new Taille("L", 20.0f),
	            new Taille("M", 30.0f),
	            new Taille("XL", 40.0f)
	        );
	        comboBoxTaille.setItems(listeDeTaille);
	        comboBoxTaille.valueProperty().addListener((obs, oldVal, newVal) -> mettreAJourVeloEtPrix());
	        

	    }
	    
	    public void setupComboBoxesMarque() {
	        ObservableList<Marque> listeDeMarque = FXCollections.observableArrayList(
	            new Marque("Decathlon", 10.0f),
	            new Marque("KTM", 20.0f),
	            new Marque("LaPierre", 30.0f),
	            new Marque("Trek", 40.0f)
	        );
	        comboBoxMarque.setItems(listeDeMarque);
	        comboBoxMarque.valueProperty().addListener((obs, oldVal, newVal) -> mettreAJourVeloEtPrix());
	        
	    }
	    
	    private void mettreAJourVeloEtPrix() {
	        if (veloActuel != null) {
	            float prixTotal = veloActuel.getPrix();
	            if (comboBoxCouleur != null && comboBoxCouleur.getValue() != null) {
	                prixTotal += comboBoxCouleur.getValue().getPrix();
	            }
	            if (comboBoxMarque != null && comboBoxMarque.getValue() != null) {
	                prixTotal += comboBoxMarque.getValue().getPrix();
	            }
	            if (comboBoxTaille != null && comboBoxTaille.getValue() != null) {
	                prixTotal += comboBoxTaille.getValue().getPrix();
	            }
	            if (oui.isSelected() && !perso.getText().isEmpty()) {
	            	Personnalisation personnalisation = new Personnalisation(perso.getText(),10.0f);
	            	veloActuel.setPersonnalisation(personnalisation);
	            	prixTotal += personnalisation.getPrix();
	            }
	            if (labelPrix != null) {
	                labelPrix.setText("Prix: " + prixTotal + " €");
	            }
	        }
	    }
	    
	    private void switchSceneWithFadeTransition(String fxmlFile, Stage stage, Velo velo) throws IOException {
	        Scene currentScene = stage.getScene();

	        if (currentScene != null) {
	            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), currentScene.getRoot());
	            fadeOut.setFromValue(1.0);
	            fadeOut.setToValue(0.0);

	            fadeOut.setOnFinished(event -> {
	                try {
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	                    Parent newRoot = loader.load();

	                    VueController controller = loader.getController();
	                    controller.initData(velo);

	                    Scene newScene = new Scene(newRoot);
	                    newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	                    stage.setScene(newScene);

	                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), newRoot);
	                    fadeIn.setFromValue(0.0);
	                    fadeIn.setToValue(1.0);
	                    fadeIn.play();

	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });

	            fadeOut.play();
	        } else {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
	            Parent newRoot = loader.load();

	            VueController controller = loader.getController();
	            controller.initData(velo);

	            Scene newScene = new Scene(newRoot);
	            newScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            stage.setScene(newScene);
	            stage.show();
	        }
	    }
	    
	    public void shakeNode(Node node) {
	        TranslateTransition tt = new TranslateTransition(Duration.millis(100), node);
	        tt.setByX(10);
	        tt.setCycleCount(4);
	        tt.setAutoReverse(true);
	        tt.playFromStart();
	    }
	    
	    private boolean isCommandeValide() {
	        if (comboBoxCouleur.getValue() == null || comboBoxTaille.getValue() == null || comboBoxMarque.getValue() == null) {
	            return false;
	        }
	        if (oui.isSelected() && (perso.getText() == null || perso.getText().isEmpty())) {
	            return false;
	        }
	        return true;
	    }

	    private void afficherPopupConfirmation() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Confirmation de commande");
	        alert.setHeaderText(null);
	        alert.setContentText("Vous avez bien commandé le vélo !");
	        alert.showAndWait();
	    }
}
