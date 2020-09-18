package uni.tbd.openday.Class;
/**
 * Added by Bottle on 18/9/2020.
 */

public class Post {
    private String title;
    private String body;
    private String author;
    private String category;

    public Post(String title, String body, String author, String category) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.category = category;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }
}
