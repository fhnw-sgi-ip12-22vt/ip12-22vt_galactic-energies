package ch.fhnw.galacticenergies.menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;

public class MainMenu extends FXGLMenu {
    public MainMenu (MenuType type) {
        super(type);

        getContentRoot().getChildren().addAll(createBackground(getAppWidth(),getAppHeight()));
    }



    protected Node createBackground(double w, double h){
        System.out.println("hallo" );
        return FXGL.texture("bg/ColorBlend.png");
    }



}
