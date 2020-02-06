/**
 * Contains information regarding food eaten.
 * 
 * @author Jackson O'Donnell
 * @version 1.0
 */

package diary_objects;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

public class FoodDiary extends Diary{
	
	//Holds a list of food eaten
	private List<Pair<Food, Integer>> foodList;
	
	/**
	 * Default constructor for the Food Diary. Starts with nothing.
	 */
	public FoodDiary() {
		foodList = null;
	}
	
	/**
	 * copy constructor for the Food Diary. Starts with nothing.
	 */
	public FoodDiary(FoodDiary fd) {
		super(fd.getDate(), fd.getNote());
		foodList = new ArrayList<Pair<Food, Integer>>(fd.getFood());
	}
	
	/**
	 * Specific constructor for the Food Diary.
	 * 
	 * @param food A list of food to add to the list.
	 */
	public FoodDiary(String date, String note, ArrayList<Pair<Food, Integer>> food) {
		super(date, note);
		foodList = new ArrayList<Pair<Food, Integer>>(food);
	}
	
	/**
	 * sets the food
	 * 
	 * @param fl a list of food and quantity pairs
	 */
	public void setFood(ArrayList<Pair<Food, Integer>> fl) {
		this.foodList = new ArrayList<Pair<Food, Integer>>(fl);
	}
	
	/**
	 * Adds a single unit of a food to the list.
	 * 
	 * @param f The type of food to add
	 */
	public void addOneFood(Food f) {
		for(Pair<Food, Integer> p : foodList) {
			if(p.getValue0().equals(f)) {
				int a = p.getValue1()+1;
				p.setAt1(a);
				return;
			}
		}
		foodList.add(new Pair(f, 1));
	}
	
	/**
	 * Adds {@code quantity} of a type of food to the list of foods.
	 * 
	 * @param f The type of food to be added
	 * @param quantity The quantity of food to be added
	 */
	public void addFood(Food f, Integer quantity) {
		for(Pair<Food, Integer> p : foodList) {
			if(p.getValue0().equals(f)) {
				int a = p.getValue1()+quantity;
				p = new Pair<Food, Integer>(p.getValue0(), a);
				return;
			}
		}
		foodList.add(new Pair(f, quantity));
	}
	
	public Integer getCalories() {
		Integer cal = 0;
		for(Pair<Food, Integer> f : foodList) {
			cal += (f.getValue0().getCalories()*f.getValue1());
		}
		return cal;
	}

	public List<Pair<Food, Integer>> getFood() {
		
		return foodList;
	}
	
	
	
}
