package ch.fhnw.galacticenergies.factories;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import ch.fhnw.galacticenergies.menu.MainMenu;

public class LoadingSceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu(){
        return new MainMenu(MenuType.MAIN_MENU);
    }
}
