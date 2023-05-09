package ch.fhnw.galacticenergies.events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

    public static final EventType<GameEvent> ANY =
            new EventType<>(Event.ANY, "GAME_EVENT");

    public static final EventType<GameEvent> PLAYER_GOT_HIT =
            new EventType<>(ANY, "PLAYER_GOT_HIT");

    public static final EventType<GameEvent> ASTEROID_GOT_HIT =
            new EventType<>(ANY, "ASTEROID_HIT");

    public GameEvent (EventType<? extends Event> eventType) {
        super(eventType);
    }
}
