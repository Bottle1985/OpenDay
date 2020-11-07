package uni.tbd.openday.Module.main.users.model;


public class User {

    private String name;

    private String email;

    private String uid;

    private String photo_url;

    private int isChucvu;

    private String chuc_vu;

    public User() {}

    public User(String uid, String name, String email,String chuc_vu, int isChucvu) {
        this.chuc_vu = chuc_vu;
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.isChucvu = isChucvu;
    }

    public int getIsChucvu() {
        return isChucvu;
    }

    public void setIsChucvu(int isChucvu) {
        this.isChucvu = isChucvu;
    }

    public String getChuc_vu() {
        return chuc_vu;
    }

    public void setChuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public User(String name) {
        this.name = name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
