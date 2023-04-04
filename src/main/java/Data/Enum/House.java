package Data.Enum;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum House implements Serializable {
    NONE,
    GRYFFONDOR,
    SLYTHERIN,
    RAVENCLAW,
    HUFFLEPUFF,
}
