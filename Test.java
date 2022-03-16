//hello there

public class Test
{
    
    public static void main(String[] args)
    {
        String[][] spare ={ 
            {"null", "null", "null", "null", "null"},
            {"null", "CellStationary 2", "CellDivide 5", "CellStationary 11", "null"},
            {"null", "CellMoveDiagonal 4", "CellMoveToggle 3","CellMoveToggle 10", "CellStationary 4"},
            {"null", "null", "CellDivide 2", "CellMoveUp 4", "null"} };

        PetriDish john = new PetriDish(spare);

        System.out.println(john);
        
    }

    /* Petri Dish toString
    
    public String toString() {
        String output = "";
        for(int x = 0; x < dish.length; x++) {
            for(int y = 0; y < dish[x].length; y++) {
                if(dish[x][y] != null) {
                    output+= (dish[x][y]).toString() + " ";
                }
                else {
                    output+= "n ";
                }
            }
            output+= "\n";
        }
        output+= "\n";

        return output;
    }

    */
}
