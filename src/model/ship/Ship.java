/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package model.ship;

public abstract class Ship {
    private int shipID; // can be used for keepying track of ship (nextID)
    private String name;
    private int size;

    //contructor with size, each ship-type(subclass) has its own size passed on here
    public Ship(String name, int size){
        this.name =name;
        this.size = size;
    }

    //getter or size used in calculations
    public int getSize() {
        return size;
    }

    //toString
    @Override
    public String toString(){
        return name;
    }
}
