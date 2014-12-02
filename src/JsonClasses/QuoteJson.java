package JsonClasses;

public class QuoteJson implements java.io.Serializable {
	private  final long serialVersionUID = 2L;
	private String overallID = "getQuote";
	private String author;
	private String quote;
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
 
	

}
