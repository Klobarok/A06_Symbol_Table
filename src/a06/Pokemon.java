package a06;

/**
 * Represents a Pokémon with its attributes and stats.
 *
 * @author Joseph Peat
 * @version 1.0
 */
public class Pokemon {
    private int id;
    private String name;
    private String type;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    /**
     * Constructs a new Pokémon object.
     *
     * @param id             the unique identifier of the Pokémon
     * @param name           the name of the Pokémon
     * @param type           the type of the Pokémon (e.g., "Grass/Poison")
     * @param hp             the hit points (HP) stat
     * @param attack         the attack stat
     * @param defense        the defense stat
     * @param specialAttack  the special attack stat
     * @param specialDefense the special defense stat
     * @param speed          the speed stat
     */
    public Pokemon(int id, String name, String type, int hp, int attack, int defense,
                   int specialAttack, int specialDefense, int speed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }





	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    @Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type=" + type + ", hp=" + hp + ", attack=" + attack
				+ ", defense=" + defense + ", specialAttack=" + specialAttack + ", specialDefense=" + specialDefense
				+ ", speed=" + speed + "]";
	}

}
