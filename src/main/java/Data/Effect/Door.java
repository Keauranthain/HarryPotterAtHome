package Data.Effect;

import Data.Admin.Game;
import Data.Room.Room;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class Door {
    private int x;
    private int y;
    private int idRoom;
    public Door(int x, int y, int idRoom){
        this.x=x;
        this.y=y;
        this.idRoom=idRoom;
    }
    public void change(int x, int y, int idRoom,Game admin){
        this.x=x;
        this.y=y;
        this.idRoom=idRoom;
        teleport(admin);
    }
    public void teleport(Game admin){
        Room roomfrom = admin.findRoomById(admin.var.idRoom);
        Room roomto = admin.findRoomById(idRoom);
        //Cleaning of the old room
        int position = 1;
        while (position<roomfrom.objects.size()&&roomfrom.objects.get(position-1)[0] != 1) {
            position++;
        }
        if (roomfrom.objects.get(position-1)[0]==1){
            roomfrom.objects.remove(position-1);
        }
        //New room
        roomto.objects.add(new int []{1,x,y});
        admin.var.idRoom = idRoom;
        admin.blackscreen(500);
        admin.ScreenMap();
    }
}
