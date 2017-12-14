package Card;

import javax.swing.*;

public class Card extends JButton {
	
	private int value;
	private boolean selected;
	
	public Card(ImageIcon image) {
		super(image);
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	
	/*
	 * selected ฐüทร method
	 */
	
	public boolean isSelected() {
		return selected;
	}
	
	public void toggleSelect() {
		this.selected = !selected;
	}
}
