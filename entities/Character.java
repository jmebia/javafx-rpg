package main.framework.entities;


public class Character extends Entity {

    private String name = "";

    private int level;
    private double experience;

    private double maxHealth;
    private double currentHealth;
    private final double minHealth = 0;

    /** ------------ Constructors --------- **/

    // default constructor
    public Character(String name, double maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;

        this.level = 1;
        this.experience = 0;
    }

    // constructor #2
    public Character(String name, double maxHealth, double currentHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;

        this.level = 1;
        this.experience = 0;
    }

    // constructor #3
    public Character(String name, double maxHealth, double currentHealth, int level, double experience) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;

        this.level = (level < 1 ? 1 : level); // if inputted level is less than 1, then this.level is equal to 1
        this.experience = experience;
    }

    /** ----------------- Setters -------------------- **/

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    /** ----------------- Getters --------------------- **/


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getExperience() {
        return experience;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

}
