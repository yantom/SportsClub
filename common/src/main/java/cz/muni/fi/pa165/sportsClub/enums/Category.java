package cz.muni.fi.pa165.sportsClub.enums;

/**
 * Represents age category
 * 
 * @author Andrej 410433
 */
public enum Category {
	MEN(200), U19(19), U17(17), U15(15), U13(13);

	private int ageLimit;

	private Category(int ageLimit){
		this.ageLimit = ageLimit;
	}

	public int getAgeLimit(){
		return ageLimit;
	}

	public Category getCategoryBelow(){
		Category category = this;
		switch(category) {
			case MEN :
				return U19;
			case U19 :
				return U17;
			case U17 :
				return U15;
			case U15 :
				return U13;
			case U13 :
				return null;
		}
		return null;
	}
}

