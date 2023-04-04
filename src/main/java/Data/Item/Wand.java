package Data.Item;

import Data.Enum.Core;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Random;

@Setter @Getter
public class Wand implements Serializable {
    public int size;
    public Core core;
    public String name;

    public Wand(){
        Random random = new Random();
        int size = random.nextInt(23) + 12;
        this.size = size;
        int alea = random.nextInt(3);
        if (alea == 0){
            this.core = Core.UNICORN_HAIR;
            this.name = "Crin de licorne, "+size+"cm";
        } else if (alea==1) {
            this.core = Core.PHOENIX_FEATHER;
            this.name = "Plume de phenix, "+size+"cm";
        }
        else if (alea==2) {
            this.core = Core.DRAGON_HEARTSTRING;
            this.name = "Coeur de Dragon, "+size+"cm";
        }
    }
}
