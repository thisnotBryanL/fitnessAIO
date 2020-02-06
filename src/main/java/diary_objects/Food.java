/**
 * Contains information about a specific food.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package diary_objects;

public class Food {

	private String name;
	private Integer calories;
	private Integer fat, protein, carbs;
	
	/**
	 * Default constructor for a Food object.
	 * Defaults to water, with no calories or macros.
	 */
	public Food(){
		name = "water";
		calories = 0;
		fat = protein = carbs = 0;
	}
	
	/**
	 * Specific constructor for a Food object.
	 * 
	 * @param n Name of the food.
	 * @param cal Number of calories in the food.
	 * @param f Number of grams of fat in the food.
	 * @param p Number of grams of protein in the food.
	 * @param c Number of grams of carbohydrates in the food.
	 */
	public Food(String n, Integer cal, Integer f, Integer p, Integer c){
		name = n;
		calories = cal;
		fat = f;
		protein = p;
		carbs = c;
	}

	/**
	 * Getter for the name of the food.
	 * 
	 * @return A String (The name of the food)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the food.
	 * 
	 * @param name The name of the food item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the calories in the food.
	 * 
	 * @return Integer containing the number of calories
	 */
	public Integer getCalories() {
		return calories;
	}

	/**
	 * Setter for the calories in the food.
	 * 
	 * @param calories The number of calories in the food.
	 */
	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	/**
	 * Getter for the fat in the food.
	 * 
	 * @return The number of grams of fat
	 */
	public Integer getFat() {
		return fat;
	}

	/**
	 * Setter for the fat in the food.
	 * 
	 * @param fat The number of grams of fat in the food
	 */
	public void setFat(Integer fat) {
		this.fat = fat;
	}

	/**
	 * Getter for the protein in the food.
	 * 
	 * @return The grams of protein in the food
	 */
	public Integer getProtein() {
		return protein;
	}

	/**
	 * Setter for the protein in the food.
	 * 
	 * @param protein The number of grams of protein in the food
	 */
	public void setProtein(Integer protein) {
		this.protein = protein;
	}

	/**
	 * Getter for carbs in the food.
	 * 
	 * @return The number of grams in the food
	 */
	public Integer getCarbs() {
		return carbs;
	}

	/**
	 * Setter for carbs in the food.
	 * 
	 * @param carbs The number of grams of carbs the food should have.
	 */
	public void setCarbs(Integer carbs) {
		this.carbs = carbs;
	}

	/**
	 * Generates a hash code for a Food object.
	 * 
	 * @return An integer representation of the hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((carbs == null) ? 0 : carbs.hashCode());
		result = prime * result + ((fat == null) ? 0 : fat.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((protein == null) ? 0 : protein.hashCode());
		return result;
	}
	
	/**
	 * A method to check if two Food objects are equal.
	 * 
	 * @param obj A secondary Food object to compare to
	 * 
	 * @return True if the two Foods are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (carbs == null) {
			if (other.carbs != null)
				return false;
		} else if (!carbs.equals(other.carbs))
			return false;
		if (fat == null) {
			if (other.fat != null)
				return false;
		} else if (!fat.equals(other.fat))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (protein == null) {
			if (other.protein != null)
				return false;
		} else if (!protein.equals(other.protein))
			return false;
		return true;
	}
	
}
