package Data.Room;

import Data.Admin.Game;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter

public class Room implements Serializable {
    private int id;
    private int X;
    private int Y;
    private String name;
    public List<int[]> objects;//id,X,Y
    public Room(int id, int x, int y, String name) {
        this.id=id;
        this.X=x;
        this.Y=y;
        List<int[]> list = new ArrayList<>();//((id1,X1,Y1),(id2,...
        this.objects= list;
        this.name = name;
    }



    public String[][] roomBuilder(Game admin){

        int x = getX();
        int y = getY();
        String[][] ArrayRoom = new String[y+8][x+14];
        for (int i = 0; i <(y+8);i++) {
            for (int k = 0; k < (x+14); k++) {
                if(i>3 && i<(y+4) && k>6 && k<(x+7)){
                    ArrayRoom[i][k]=" ";
                }
                else {
                    ArrayRoom[i][k]="X";
                }
            }
        }
        for (int i = 0;i<objects.size() ;i++){
            ArrayRoom[objects.get(i)[2]+4][objects.get(i)[1]+7] = admin.findGameObjectById(objects.get(i)[0]).getMarker();
        }
        return ArrayRoom;
    }
    public void printRoom(Game admin) {
        String[][] room = roomBuilder(admin);
        for (int i = 0; i < room.length; i++) {
            System.out.println(Arrays.toString(room[i]));
        }
    }
}


