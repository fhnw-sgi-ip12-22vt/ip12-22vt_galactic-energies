package ch.fhnw.galacticenergies.menu;

import ch.fhnw.galacticenergies.data.DBConnection;
import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

/**
 * Creates the Main Menu
 *
 * @Version 1.0
 */

public class MainMenu extends FXGLMenu {

    private Animation<?> animation;
    private static Label leaderboardAllTime;
    private static Label leaderboardToday;

    private static Label allTime;

    static Button btnPlay = new Button("Play");
    static Button btnIntro = new Button("Intro");



    private int id = 1;

    private static DecimalFormat df = new DecimalFormat("#.####");

    public MainMenu(MenuType type) {
        super(type);

        // Add background texture, set its fit width and height to app width and height respectively
        Texture bg = getAssetLoader().loadTexture("bg/SpaceBackground.jpg");
        bg.setFitWidth(getAppWidth());
        bg.setFitHeight(getAppHeight());

        // Create title label with custom font and size, set its color to white
        Label title = new Label("Galactic Energies");
        title.setFont(Font.font("Broadway", 48));
        title.setTextFill(Color.WHITE);

        // Create HBox for title, set its width to the app width, align to center and position it on Y axis
        HBox titleHBox = new HBox(title);
        titleHBox.setPrefWidth(getAppWidth());
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setTranslateY(50);

        // Create Play button, add custom style class, and set its on-click action to fireNewGame()
        btnPlay.getStyleClass().add("main_menu_button");
        // fireNewGame() clears the Scene and calls initGame(), to spawn all entities.
        btnPlay.setOnAction(e -> fireNewGame());

        // Create Intro button, add custom style class, and set its on-click action to start IntroScene
        btnIntro.getStyleClass().add("main_menu_button");
        btnIntro.setOnAction(e -> {
            disableButtons();
            IntroScene.start();
        });


        // Creates a VBox to hold the menu buttons, sets its properties and adds it to the content root
        VBox buttonVBox = new VBox(5, btnPlay, btnIntro);
        buttonVBox.getStyleClass().add("main_menu_VBox");
        buttonVBox.setPrefWidth(getAppWidth());
        buttonVBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonVBox.setTranslateY(getAppHeight() * 0.6);
        getContentRoot().getChildren().addAll(bg, titleHBox, buttonVBox);

        /**
         * Update method for the HighScoresScene, removes and recreates the hiscores display and updates animation.
         */
        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

    }

    /**
     * This method is called when the application is first created.
     * It sets up the animation and stops it, then starts it again
     */
    @Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
        id = (int) (Math.random() * (31 - 15)) + 15;

        getContentRoot().getChildren().removeAll(leaderboardAllTime, leaderboardToday, allTime);
        createLeaderboardAllTime();
        createLeaderboardToday();
        createAllTime();
        getContentRoot().getChildren().addAll(leaderboardAllTime, leaderboardToday, allTime);


        // getContentRoot().getChildren().add(createLeaderboard());
    }

    /**
     * Update method for the HighScoresScene, removes and recreates the hiscores display and updates animation.
     *
     * @param tpf
     */
    @Override
    protected void onUpdate(double tpf) {
        animation.onUpdate(tpf);
    }

    /**
     * Create leaderboard label with styling and positioning
     */
    public void createLeaderboardAllTime() {

        Label leaderboard = new Label();
        leaderboard.getStyleClass().add("leaderboard");
        leaderboard.setWrapText(true);
        leaderboard.setLayoutX(getAppWidth() * 0.2);
        leaderboard.setLayoutY(getAppHeight() * 0.25);

        // Establish a connection to the database
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        String result = "Leaderboard All Time \nDate:             Score: \n";

        // Retrieve top 5 rows of total power production from the database and format them as a string
        try {

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower ORDER BY producedpower DESC LIMIT 5");

            while (rs.next()) {
                Date date = rs.getDate("date");
                Double power = rs.getDouble("producedpower");

                result = result + date + "   " + df.format(power) + "  Wh \n";
            }

            conn.close();

            // Catch any SQL exceptions, print error message and rethrow as RuntimeException
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

        leaderboard.setText(result);
        this.leaderboardAllTime = leaderboard;

    }

    /**
     * Create leaderboard label with styling and positioning
     */
    public void createLeaderboardToday() {

        Label leaderboard = new Label();
        leaderboard.getStyleClass().add("leaderboard");
        leaderboard.setWrapText(true);
        leaderboard.setLayoutX(getAppWidth() * 0.55);
        leaderboard.setLayoutY(getAppHeight() * 0.25);

        // Establish a connection to the database
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        String result = "Leaderboard Today \nDate:             Score: \n";

        // Retrieve top 5 rows of total power production from the database and format them as a string
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower WHERE date = curdate() ORDER BY producedpower DESC LIMIT 5");

            while (rs.next()) {
                Date date = rs.getDate("date");
                Double power = rs.getDouble("producedpower");

                result = result + date + "   " + df.format(power) + "  Wh \n";
            }

            conn.close();

            // Catch any SQL exceptions, print error message and rethrow as RuntimeException
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

        leaderboard.setText(result);
        this.leaderboardToday = leaderboard;

    }

    /**
     * Method to create the "ALL Time" power label
     */
    public void createAllTime() {
        Label allTime = new Label();
        allTime.getStyleClass().add("leaderboard");
        allTime.setWrapText(true);
        allTime.setLayoutX(getAppWidth() * 0.2);
        allTime.setLayoutY(getAppHeight() * 0.75);

        // Establish a connection to the database
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        // Initialize variables for total power and device name/power
        String result = "All Time Power: ";

        double totalpower = 0;
        String deviceName = "";
        int devicePower = 0;

        // Try-catch block for SQL query and result set
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower");
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM energydata WHERE idenergydata =" + id);


            while (rs.next()) {
                totalpower = totalpower + rs.getDouble("producedpower");
            }
            // Add the total produced power to the result string
            result = result + df.format(totalpower) + " Wh \n";

            while (rs2.next()) {
                deviceName = (rs2.getString("devicename"));
                devicePower = rs2.getInt("power");
            }
            double timeToUse = totalpower / devicePower;
            // Calculate the usage time for the device based on the total produced power and device power
            result = result + "You could use a " + deviceName + " for: " + (int)(timeToUse) + "h " + df.format((timeToUse - (int)timeToUse)*60) + " min";

            // Close the connection
            conn.close();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

        // Set the text of the label to the result string and store the label as a member variable
        allTime.setText(result);
        this.allTime = allTime;
    }

    public static void enableButtons() {
        btnIntro.disableProperty().set(false);
        btnPlay.disableProperty().set(false);
    }
    public void disableButtons() {
        btnIntro.disableProperty().set(true);
        btnPlay.disableProperty().set(true);
    }

}