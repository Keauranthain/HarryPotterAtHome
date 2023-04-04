package Data.Admin;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class SaveList implements Serializable {
    List<String> Savegame;
    public SaveList(){
        this.Savegame=new ArrayList<>();
    }
}
