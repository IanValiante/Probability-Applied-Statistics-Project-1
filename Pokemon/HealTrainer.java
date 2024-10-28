class HealTrainer extends Trainer {
	 private int healingAmount;

	    public HealTrainer(String name, int healingAmount) {
	        super(name);
	        this.healingAmount = healingAmount;
	    }

	    public void use(Player player) {
	        Pokemon activePokemon = player.getActivePokemon();
	        if (activePokemon != null) {
	            activePokemon.heal(healingAmount);
	            System.out.println(player.getName() + " uses " + this + ". " +
	                    activePokemon.getName() + " heals for " + healingAmount + " HP.");
	        } else {
	            System.out.println(player.getName() + " has no active Pokémon to heal!");
	        }
	    }
    @Override
    public String toString() {
        return "Heal Trainer: " + getName() + " (Heals " + healingAmount + ")";
    }
}