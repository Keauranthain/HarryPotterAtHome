package Data.Item;

import Data.Admin.Game;
import Data.Enum.House;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Potion implements Serializable {
    public int id;
    public String name;
    public int level;
    public String type;
    public Potion (int id, String name, String type, int level){
        this.id=id;
        this.name=name;
        this.level=level;
        this.type=type;
    }
    public int heal(Game admin){
        int heal = 0;
        if (admin.var.wizard.getHouse().name().equals("HUFFLEPUFF")) {
            heal = 22*level;
        }
        else {
            heal = 15*level;
        }
        return 15*level;
    }
    public int accurency(Game admin){
        int result = 0;
        if (admin.var.wizard.getHouse().name().equals("HUFFLEPUFF")) {
            result =  3 * level;
        }
        else {
            result = 2 * level;
        }
        return result;
    }
    public int magic(Game admin){
        int result = 0;
        if (admin.var.wizard.getHouse().name().equals("HUFFLEPUFF")) {
            result = level * 6;
        }
        else {
            result = level * 4;
        }
        return result;
    }
    public int resist(Game admin){
        int result = 0;
        if (admin.var.wizard.getHouse().name().equals("HUFFLEPUFF")) {
            result = level * 6;
        }
        else {
            result = level * 4;
        }
        return result;
    }
}
