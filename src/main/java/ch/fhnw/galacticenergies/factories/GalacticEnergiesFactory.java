package ch.fhnw.galacticenergies.factories;

import ch.fhnw.galacticenergies.components.BackgroundStarsViewComponent;
import ch.fhnw.galacticenergies.components.DashboardComponent;
import ch.fhnw.galacticenergies.components.RocketComponent;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ch.fhnw.galacticenergies.enums.GalacticEnergiesType.*;
public class GalacticEnergiesFactory implements EntityFactory {

    private LazyValue<Image> image;

    public GalacticEnergiesFactory()
    {
        this.image = new LazyValue<>(() -> {
            var images = IntStream.rangeClosed(1, 13)
                    .mapToObj(i -> image("dashboard/Steuerboard Level " + i + ".png"))
                    .collect(Collectors.toList());
            return ImagesKt.merge(images);
        });
    }

    @Spawns("background")
    public Entity newBackground(SpawnData data)
    {
        return entityBuilder(data)
                .type(BACKGROUND)
                .with(new IrremovableComponent())
                .with(new BackgroundStarsViewComponent(
//                        texture("bg/bg_blue1.png"),
                        texture("bg/ColorBlend.png"),
                        texture("bg/Stars3.png")
//                        texture("bg/StarsBackground1.png"),
//                        texture("bg/StarsBackground2.png")
                ))
                .build();
    }

    @Spawns("rocket")
    public Entity newRocket(SpawnData data)
    {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        Texture texture = texture("Rocket.png");
        texture.setPreserveRatio(true);
        texture.setFitWidth(70);

        return entityBuilder(data)
                .type(ROCKET)
                .at(10, getAppHeight() / 2 - (texture.getFitHeight() / 2))
                .viewWithBBox(texture)
                .collidable()
                .with(physics)
                .with(new EffectComponent())
                .with(new RocketComponent(texture("bat_hit.png")))
                .build();
    }

    @Spawns("dashboard")
    public Entity newDashboard(SpawnData data)
    {
        Texture texture = texture("dashboard/Pfeil Neutral.png");
        texture.setPreserveRatio(true);
        texture.setFitHeight(20);

        StackPane root = new StackPane();
        int i = 10;
        while (i<100){
            i += 10;
        }
        LinearGradient g = LinearGradient.valueOf(
                "from 100.0% 100.0% to 100.0% 0.0% "+    // from top to bottom
                        "rgb(148, 0, 0) 50%, "+               // red at the top
                        "rgb(148, 0, 0) " + i+ "%, "+  // red at percentage
                        "rgb(14, 147, 0) "+  80 + "%, "+ // green at percentage
                        "rgb(14, 147, 0) 5%"              // green at the bottom
        );


        Rectangle r = new Rectangle(500, 500);
        r.setFill(g);

        Path semiRing = drawSemiRing(0, 0, 100, 80, g, Color.DARKGREEN);
        root.getChildren().add(semiRing);
        root.setAlignment(semiRing, Pos.CENTER);
        //root.getChildren().add(r);

        return entityBuilder(data)
                .type(DASHBOARD)
                .at(getAppWidth() / 2 - DashboardComponent.getBUTTON_SIZE(), getAppHeight() - DashboardComponent.getBUTTON_SIZE())
                .with(new DashboardComponent())
                .build();
    }

    private Path drawSemiRing(double centerX, double centerY, double radius, double innerRadius, LinearGradient bgColor, Color strkColor) {
        Path path = new Path();
        path.setFill(bgColor);
        path.setFillRule(FillRule.EVEN_ODD);

        MoveTo moveTo = new MoveTo();
        moveTo.setX(centerX + innerRadius);
        moveTo.setY(centerY);

        ArcTo arcToInner = new ArcTo();
        arcToInner.setX(centerX - innerRadius);
        arcToInner.setY(centerY);
        arcToInner.setRadiusX(innerRadius);
        arcToInner.setRadiusY(innerRadius);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerRadius);
        moveTo2.setY(centerY);

        HLineTo hLineToRightLeg = new HLineTo();
        hLineToRightLeg.setX(centerX + radius);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radius);
        arcTo.setY(centerY);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);

        HLineTo hLineToLeftLeg = new HLineTo();
        hLineToLeftLeg.setX(centerX - innerRadius);

        path.getElements().add(moveTo);
        path.getElements().add(arcToInner);
        path.getElements().add(moveTo2);
        path.getElements().add(hLineToRightLeg);
        path.getElements().add(arcTo);
        path.getElements().add(hLineToLeftLeg);

        return path;
    }

}
