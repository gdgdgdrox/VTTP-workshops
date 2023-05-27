package day21.books.model;

public class Book {
    private String bookId;
    private String title;
    private String description;
    private String image;
    private Float rating;


    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
            " bookId='" + getBookId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }

}
