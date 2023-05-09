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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

/**
 * Creates the Main Menu
 *
 * @Version 1.0
 */

public class MainMenu extends FXGLMenu {

    private Animation<?> animation;
    private static Label leaderboard;

    private static Label allTime;

    private int id = 1;

    private static DecimalFormat df = new DecimalFormat("#.####");

    public MainMenu(MenuType type) {
        super(type);

        Texture bg = getAssetLoader().loadTexture("bg/SpaceBackground.jpg");
        bg.setFitWidth(getAppWidth());
        bg.setFitHeight(getAppHeight());

        Label title = new Label("Galactic Energies");
        title.setFont(Font.font("Broadway", 48));
        title.setTextFill(Color.WHITE);

        HBox titleHBox = new HBox(title);
        titleHBox.setPrefWidth(getAppWidth());
        titleHBox.setAlignment(Pos.CENTER); titleHBox.setTranslateY(50);

        Button btnPlay = new Button("Play");
        btnPlay.getStyleClass().add("main_menu_button");
        // fireNewGame() clears the Scene and calls initGame(), to spawn all entities.
        btnPlay.setOnAction(e -> fireNewGame());

        Button btnExit = new Button("Exit");
        btnExit.getStyleClass().add("main_menu_button");
        btnExit.setOnAction(e -> fireExit());

        Button btnIntro = new Button("Intro");
        btnIntro.getStyleClass().add("main_menu_button");
        btnIntro.setOnAction(e -> IntroScene.start());

        //Label leaderboard = createLeaderboard();

        VBox buttonVBox = new VBox(5, btnPlay, btnLeaderboard, btnIntro, btnExit);
        buttonVBox.getStyleClass().add("main_menu_VBox");
        buttonVBox.setPrefWidth(getAppWidth());
        buttonVBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonVBox.setTranslateY(getAppHeight() * 0.6);
        getContentRoot().getChildren().addAll(bg, titleHBox, buttonVBox);


        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

    }

    @Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
        id = (int) (Math.random() * (14 - 1)) + 1;


        // getContentRoot().getChildren().add(createLeaderboard());
    }

    @Override
    protected void onUpdate(double tpf) {
        getContentRoot().getChildren().removeAll(leaderboard, allTime);
        createLeaderboard();
        createAllTime();
        getContentRoot().getChildren().addAll(leaderboard, allTime);

        animation.onUpdate(tpf);
    }

    public void createLeaderboard() {

        Label leaderboard = new Label();
        leaderboard.getStyleClass().add("leaderboard");
        leaderboard.setWrapText(true);
        leaderboard.setLayoutX(getAppWidth() * 0.3);
        leaderboard.setLayoutY(getAppHeight() * 0.25);

        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        String result = "Leaderboard \nDate:             Score: \n";

        try {

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower ORDER BY producedpower DESC LIMIT 5");

            while (rs.next()) {
                Date date = rs.getDate("date");
                Double power = rs.getDouble("producedpower");

                result = result + date + "   " + df.format(power) + "  Wh \n";
            }

            conn.close();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

        leaderboard.setText(result);
        this.leaderboard = leaderboard;

    }

    /**
     * Method to create the"ALL Time
     */
    public void createAllTime() {
        Label allTime = new Label();
        allTime.getStyleClass().add("leaderboard");
        allTime.setWrapText(true);
        allTime.setLayoutX(getAppWidth() * 0.3);
        allTime.setLayoutY(getAppHeight() * 0.75);

        // Establish a connection to the database
        DBConnection c = new DBConnection();
        Connection conn = c.getConnection();

        String result = "All Time Power: ";

        double totalpower = 0;
        String deviceName = "";
        int devicePower = 0;

        try {
            int id = 1;

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM totalpower");
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM energydata WHERE idenergydata =" + id);


            while (rs.next()) {
                totalpower = totalpower + rs.getDouble("producedpower");
            }
            result = result + df.format(totalpower) + " Wh \n";

            while (rs2.next()) {
                deviceName = (rs2.getString("devicename"));
                devicePower = rs2.getInt("power");
            }
            result = result + "This equals to the Usage of a " + deviceName + " for: " + df.format(totalpower / devicePower) + "h";

            conn.close();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }


        allTime.setText(result);
        this.allTime = allTime;
    }

}




