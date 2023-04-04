package Data.Room;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GameObject implements Serializable {
    private int id;
    private String marker;
    private boolean obstacle;
    private int effect;
    private char direction;

    public GameObject(int id, String marker, boolean obstacle) {
        this.id=id;
        this.marker=marker;
        this.obstacle=obstacle;
        this.effect=0;
    }
    public GameObject(int id, String marker, boolean obstacle, int effect){
        this.id=id;
        this.marker=marker;
        this.obstacle=obstacle;
        this.effect=effect;
    }

}
