import java.util.List;
import java.util.Random;

/**
 * A simple model of grass.
 * Grass can grow, die from fire or being eatin.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Grass extends Organism
{
    // Characteristics shared by all grass (class variables).
    
    // The grass reproducing each turn
    private static final double SPREAD_PROBABILITY = 0.5;
    // the maximum number of vegetable units per square.
    private static final int MAX_VEGETABLE_UNITS = 10;
    // Grass has a 75% change of dying to fire.
   private static final double FIRE_DEATH_PROBABILITY = 0.75;
    // A shared random number generator to control growing.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // Track grass age  to grow every 2 turns
    private int age;
    /**
     * Create new grass. Grass is createdis created to 0 age.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Grass(Field field, Location location)
    {
        super(field, location);
        age = 0;
    }
    
    /**
     * This is what the grass does most of the time - it grows 
     * around. Sometimes it will breed or die of old age.
     * @param newGrass A list to return newly grown grass.
     */
    public void act(List<Organism> newGrass)
    {
        incrementAge();
        if(isAlive()) {
           spead(newGrass);
        }
    }
    /** use to check when the grass can grown
    
    private void incrementAge()
    {
        age++;
    }
    /**
     * Check whether or not this grass can grow into adjacent squares.
     * New grass will grow into free adjacent locations.
     * @param newGrass A list to return newly grown grass.
     */
    private void spread(List<Organism> newGrass)
    {
        // grow grass every two turns
        if (age % 2 == 0){
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        for(Location loc : free)
            Grass youngGrass = new Grass(field, loc);
            newGrass.add(youngGrass);
        }
    }
        
    /**
     * Generates a fire
     */
    public void Fire()
    {
        if(rand.nextDouble() <= FIRE_DEATH_PROBABILITY) {
            setDead();
    }

}
 /** Check that the maximum of vegetable in a particular location

    
