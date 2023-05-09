package ch.fhnw.galacticenergies.factories;

import ch.fhnw.galacticenergies.menu.MainMenu;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

/**
 * A factory for creating the loading scene in the game
 */
public class LoadingSceneFactory extends SceneFactory {

    /**
     * Creates a new main menu
     *
     * @return the newly created main menu
     */
    @Override
    public FXGLMenu newMainMenu () {
        return new MainMenu(MenuType.MAIN_MENU);
    }
}

