package fr.excilys.cdb.view.menu;

public enum MenuChoice {
	ALL_COMPUTER(1),
	ALL_COMPANY(2),
	COMPUTER_DETAILS(3),
	ADD_COMPUTER(4),
	UPDATE_COMPUTER(5),
	DELETE_COMPUTER(6),
	QUIT(8);

	private final int id;
	
	MenuChoice(int id) {
		this.id = id;
	}

	public static MenuChoice getById(int id) {
		MenuChoice[] menuChoices = MenuChoice.values();
		int i = 0;
		
		while(i < menuChoices.length) {
			if(menuChoices[i].getId() == id) {
				return menuChoices[i];
			}else {
				i ++;
			}
			
		}
		
		return null;		
	}
	
	public int getId() {
		return this.id;
}

}