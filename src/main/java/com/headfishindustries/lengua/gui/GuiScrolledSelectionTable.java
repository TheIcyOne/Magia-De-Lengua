package com.headfishindustries.lengua.gui;

import java.util.ArrayList;

import com.headfishindustries.lengua.Lengua;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiScrolledSelectionTable extends Gui{
	private final String[] header;
	public final int size;
	private ArrayList<String[]> body = new ArrayList<String[]>();
	
	private final int id;
    private final FontRenderer fontRenderer;
    public int x;
    public int y;
    /** The width of this text field. */
    public int width;
    public int height;
    /** Height of each row of text. */
    public int rowHeight;
    private int[] columnWidths;
    private int scrollDist = 0;
    private int maxStringLength = 64;
    private boolean enableBackgroundDrawing = true;
    
    //TODO: Scrolling with keyboard.
    /** if true the textbox can lose focus by clicking elsewhere on the screen */
    private boolean canLoseFocus = true;
    /** If this value is true along with isEnabled, keyTyped will process the keys. */
    private boolean isFocused;
    /** If this value is true along with isFocused, keyTyped will process the keys. */
    private boolean isEnabled = true;
    private int headColor = 0x020202;
    private int aColor = 0x343434;
    private int bColor = 0x212121;
    /** True if this textbox is visible */
    private boolean visible = true;
	
	/** Defines a new GuiScrolledSelectionTable. Header is immutable so a new instance will be required for different headers.
	 *  **/
	public GuiScrolledSelectionTable(int componentId, final String[] head, FontRenderer fontrendererObj, int x, int y, int width, int height, int rowHeight) {
		this.id = componentId;
		this.fontRenderer = fontrendererObj;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rowHeight = rowHeight;
		this.header = head;
		this.size = head.length;
		this.columnWidths = new int[this.size]; 
		for (int i = 0; i < this.size; i++) {
			columnWidths[i] = this.width / this.size;
		}
	}
	
	public GuiScrolledSelectionTable setColumnWidths(int[] widths) {
		if (widths.length != this.columnWidths.length) {
			Lengua.LOGGER.error("Passed array " + widths + " has incorrect length " + widths.length + ". Expected array of length " + this.columnWidths.length);
		}else {
			this.columnWidths = widths;
		}
		return this;
	}
	
	public GuiScrolledSelectionTable append(String[] row) {
		this.body.add(row);
		return this;
	}
	
	public GuiScrolledSelectionTable setRow(int index, String[] val) {
		this.body.set(index, val);
		return this;
	}
	
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        boolean flag = mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height;
        
        
        
		return flag;
    }
	
	public void draw() {
		this.drawHeader();
		for (int i = 0; i < this.body.size(); i++) {
			this.drawRow(i);
		}
	}
	
	private void drawRow(int index) {
		String[] row = this.body.get(index);
		if ((index + 1) * this.rowHeight - this.scrollDist > this.height) return;
		if (row == null) {
			row = new String[this.size];
			for (int i = 0; i < this.size; i++) {
				row[i] = " ";
			}
		}
		
		drawRect(this.x, this.y + (index + 1) * this.rowHeight - this.scrollDist, this.x + this.width, this.y + (index + 2) * this.rowHeight - this.scrollDist, index % 2 == 0 ? this.aColor : this.bColor);
	}
	
	private void drawHeader() {
		int index = -1;
		drawRect(this.x, this.y + (index + 1) * this.rowHeight - this.scrollDist, this.x + this.width, this.y + (index + 2) * this.rowHeight - this.scrollDist, this.headColor);
		drawStringArray(this.header, this.x, (int) Math.floor(this.y + (index + 0.5) * this.rowHeight - this.scrollDist));
	}
	
	private void drawStringArray(String[] str, int x, int y) {
		int len = 0;
		for (int i = 0; i < str.length; i++) {
			this.fontRenderer.drawStringWithShadow(str[i], x + len, y, 0xABABAB);
			len += this.columnWidths[i];
		}
		
	}
}
