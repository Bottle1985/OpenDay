package uni.tbd.openday.Class;
/**
 * Added by Bottle on 18/9/2020.
 */
public class Comment {
    public String author;
    public String body;
    public String post;

    public Comment(String author, String body, String post) {
        this.author = author;
        this.body = body;
        this.post = post;
    }

    public Comment() {}

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public String getPost() {
        return post;
    }
}
