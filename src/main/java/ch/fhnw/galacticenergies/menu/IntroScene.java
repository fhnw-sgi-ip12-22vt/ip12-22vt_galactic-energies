package ch.fhnw.galacticenergies.menu;

import ch.fhnw.galacticenergies.factories.LoadingSceneFactory;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLScene;
import com.almasb.fxgl.cutscene.Cutscene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public final class IntroScene extends GameApplication {

    public static void start(){
        var lines = getAssetLoader().loadText("intro.txt");

        var cutscene = new Cutscene(lines);


        getCutsceneService().startCutscene(cutscene);
    }


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Galactic Energies");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
    }



    @Override
    protected void initGame() {
        Texture bgTexture = FXGL.texture("bg/SpaceBackground.jpg");
        bgTexture.setFitWidth(getAppWidth());
        bgTexture.setFitHeight(getAppHeight());

        FXGLScene scene = getGameScene();
      //  scene.set
       // getGameScene().setBackgroundColor(Color.BLACK);



    }

}
