/*
 * Creates a page object for use in outputting HTML. The multiple constructors
 * support the different kind of pages that can exist: number of options, whether
 * it has a picture, if it results in a game over, etc.
 */
public class Page {
	private int pageNum;
	private String story;
	private boolean hasPic;
	private String pic; // path to the picture
	private int end; // 0=no end, 1=only end, 2=end with an alt option
	private int numOp; // number of ways to progress from this page
	 
	private String op1Text; // op1 story
	private int op1GoTo; // page to go to when this is chosen
	private String op2Text; // ...
	private int op2GoTo;
	private String op3Text;
	private int op3GoTo;
	
	// Removes unexpected/unwanted newlines, tabs, and spaces
	public int formatInt(String s) {
		s = s.replaceAll("\n", "").replaceAll("\t", "").replaceAll("\\s", "");		
		return Integer.valueOf(s);
	}
	
	public String formatString(String s) {
		s = s.replaceAll("\n", " ").replaceAll("\t", " ");
		s = "\'" + s;
		s += "\'";
		
		return s;
	}
	
	public String formatPic(String s) {
		s = s.replaceAll("\\s", "");
		s = "\'" + s;
		s += "\'";
		return s;
	}
	
	public Page(String pageNum, String pic, String story, int end, int numOp, String op1Text,
				String op1GoTo, String op2Text, String op2GoTo, String op3Text, String op3GoTo) {
		this.pageNum = formatInt(pageNum);		
		this.end = end;
		this.numOp = numOp;
		
		if(story != null)
			this.story = formatString(story);
		if(pic != null) {
			this.pic = formatPic(pic);
			this.hasPic = true;
		}
		else
			this.pic = "\'0.png\'";
		
		if(op1Text != null)
			this.op1Text = formatString(op1Text);
		else
			this.op1Text = "'Continue...'";
		
		if(op1GoTo != null)
			this.op1GoTo = formatInt(op1GoTo);
		
		if(op2Text != null)
			this.op2Text = formatString(op2Text);
		if(op2GoTo != null)
			this.op2GoTo = formatInt(op2GoTo);
		
		if(op3Text != null)
			this.op3Text = formatString(op3Text);
		if(op3GoTo != null)
			this.op3GoTo = formatInt(op3GoTo);
		
		if(end >= 1) {
			this.op1Text = "\'THE END\'";
			this.op1GoTo = 1;
		}
		else if(end == 2) {
			System.out.println("HI");
		}
		
	}
	
	public String toString() {
		return "[" + pageNum + "," + story + ", " +  hasPic + ", " + pic 
				+ ", " +  end + ", " + numOp + ", " + op1Text
				+ ", "+ op1GoTo + ", " + op2Text + ", " + op2GoTo
				+ ", " + op3Text + ", " + op3GoTo + "]";
	}
}