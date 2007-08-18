package libcafe;

import java.util.LinkedList;
import java.util.List;

public class Book extends Entity {

	private String title; // 1

	String coverImageUrl;
	String coverImageUrlThumbnail;

	String creator; // 2
	String translator; // 3
	String publisher; // 4
	Date publishDate; // 5
	String category; // 6
	String description; // 7
	String isbn; // 8
	String price; // 9

	private int rating;

	boolean isBorrowed;

	List<Tag> tags = new LinkedList<Tag>();
	List<BookListener> listeners = new LinkedList<BookListener>();

	public Book() {
		super();
	}

	public Book(int id) {
		super(id);
	}

	public void setTitle(String title) {
		this.title = title;
		notifyListeners("Title", title);
	}

	public String getTitle() {
		return title;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getCoverImageUrlThumbnail() {
		return coverImageUrlThumbnail;
	}

	public void setCoverImageUrlThumbnail(String coverImageUrlThumbnail) {
		this.coverImageUrlThumbnail = coverImageUrlThumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String author) {
		this.creator = author;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowered) {
		this.isBorrowed = isBorrowered;
		notifyListeners("isBorrowed", new Boolean(isBorrowered));
	}

	private void notifyListeners(String name, Object value) {
		for (BookListener listener : listeners) {
			listener.eventNotified(new BookEvent(this, name, value));
		}
	}

	public void addTag(Tag tag) {
		if (tags.contains(tag)) {
			return;
		}
		tags.add(tag);
		tag.add(this);
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.remove(this);
	}

	public void addListener(BookListener bookListener) {
		listeners.add(bookListener);
	}

	public void setRating(int rating) {
		this.rating = rating;
		notifyListeners("Rating", rating);
	}

	public int getRating() {
		return rating;
	}

	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return "Book=[" + title + "," + creator + "]";
	}
}
