package Data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter @Getter
public class Spell implements Serializable {
    public int idSpell;
    public String name;
    public int base_damage ;
    public int accuracy;

    public Spell (int idSpell, String name, int base_damage, int accuracy){
        this.idSpell=idSpell;
        this.name=name;
        this.base_damage=base_damage;
        this.accuracy=accuracy;
    }
}
