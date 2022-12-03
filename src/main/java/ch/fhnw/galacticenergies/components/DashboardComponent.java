package ch.fhnw.galacticenergies.components;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.ImagesKt;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.*;

public class DashboardComponent extends Component {
    private ArrayList<Texture> images = new ArrayList<>();

    private Texture currentImage = new Texture(image("dashboard/Steuerboard Level 1.png"));

    public DashboardComponent() {
        IntStream.rangeClosed(1, 13).forEach(i -> {
            Texture t = texture("dashboard/Steuerboard Level " + i + ".png");
            t.setPreserveRatio(true);
            t.setFitHeight(100);

            images.add(t);
        });
    }

    @Override
    public void onUpdate(double tpf) {

    }

    public void setSpeedImage(int i) {
        entity.getViewComponent().removeChild(currentImage);

        entity.getViewComponent().addChild(images.get(i));
        currentImage = images.get(i);
    }

}
