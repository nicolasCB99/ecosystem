import java.util.List;
import java.util.Random;

/**
* A simple model of grass.
* Grass can grow, die from fire or be eatin.
* 
 * @author David J. Barnes and Michael Kölling
* @version 2016.02.29 (2)
*/
public class Grass extends Animal
{
    // Characteristics shared by all grass (class variables).
    
    // The grass reproducing every other turn
    private static final int SPREAD_PROBABILITY = 2;
    // the maximum number of vegetable units per square.
    private static final int MAX_VEGETABLE_UNITS = 10;
    // Grass has a 75% change of dying to fire.
   private static final double FIRE_DEATH_PROBABILITY = 0.75;
    // A shared random number generator to control growing.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    

    
    // The grass age.
    private int growth;
    //If the grass is being eaten
    private boolean eaten;

    /**
     * Create a new instance of grass. 
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Grass(Field field, Location location)
    {
        super(field, location);
        growth = 0;
        eaten = false;
        }
    }
    
    /**
     * This is what the grass does most of the time - it grows, 
     * reproduces, and can regrown after being eaten.
     * @param newGrass A list to return newly grown grass.
     */
    public void act(List<Animal> newGrass)
    {
        if(isAlive()) {
            growth++;            
            if(growth >= SPREAD_PROBABILITY && !eaten) {
                reproduce(newGrass);
                growth = 0;
            }
            if(eaten) { 
                regrow();
        }
    }
   
    /**
     * handles the growth of grass into free adjacent locations.
     * @param newGrass A list to return newly grown grass.
     */
    private void reproduce(List<Animal> newGrass)
    {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        
        for(Location loc : free) {
            if(field.isVegetationLimitReached(loc, MAX_VEGETABLE_UNITS)) {
                continue;
        }
            if (rand.nextBoolean() && field.getObjectAt(loc) == null) {
                Grass newUnit = new Grass(field, loc);
                newGrass.add(newUnit);
                Field.place(newUnit, loc);
                break;
            }
        }
    }
     /**
     *Label grass as eaten so that it can regrow
     */
        public void markAsEaten()
            {
            eaten = true;
        }
    /**
     * Generate regrowth after being eaten
     */
    private void regrow()
    {
   eaten = false;
    }

    /**
     * kills the grass in a fire.
     */
    public void killedByFire()
    {
       if (rand.nextDouble() <= FIRE_DEATH_PROBABILITY)
       {
           setDead();
       }
    }
}
