import Data.Admin.Game;
import Data.Admin.InitialConfig;
import Data.Admin.Save;
import Data.Story;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Game admin = new Game();
        admin.save.start(admin);
        admin.creation.Creator(admin);
        Story story = new Story();
        //fight.fight();
        story.allstory(admin);
        //Data.Room.Screen.ScreenMap(MC.getMapID());
        //Data.Room.Screen.objectXY(1);
        //Data.Data.Room.Room.printRoom(1);
    }
}

