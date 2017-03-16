package GraphOptimazer;

/**
 * Created by Vladislav on 12/03/2017.
 */
public class Position {
    private Integer rowIndex;
    private Integer columnIndex;

    Position(Integer i, Integer j){
        rowIndex = i;
        columnIndex = j;
    }

    public Integer getRowIndex(){
        return rowIndex;
    }

    public Integer getColumnIndex(){
        return columnIndex;
    }
}
