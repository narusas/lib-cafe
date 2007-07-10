package libcafe;

import java.util.Date;

public class Book {

	private String title;

	String coverImageUrl;
	String coverImageUrlThumbnail;
	String description;
	String author;
	String translator;
	String publisher;
	Date publishDate;
	String category;
	String isbn;
	int price;

	boolean isBorrowered;

	public void setTitle(String title) {
		this.title = title;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isBorrowered() {
		return isBorrowered;
	}

	public void setBorrowered(boolean isBorrowered) {
		this.isBorrowered = isBorrowered;
	}

}
