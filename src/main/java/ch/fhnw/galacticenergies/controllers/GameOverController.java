package ch.fhnw.galacticenergies.controllers;

import static com.almasb.fxgl.dsl.FXGL.showConfirm;
import static javafx.application.Platform.exit;

import ch.fhnw.galacticenergies.View;
import com.almasb.fxgl.app.GameApplication;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.scene.*;

public class GameOverController {

    public static void showGameOver(){
        showConfirm("Game Over! You're Score was:  "+ (int)PowerController.getTotalPower()+" Restart?", yes -> {
            if (yes) {
                //TODO restart game
            } else {
                exit();
            }
        });

    }


}
