package aiss.YouTubeMiner.model.videoMiner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "VMUser")
public class User {

    /*
    * In order to avoid making the model unnecessarily complex, we establish a one-to-one relationship between aiss.videominer.models.vimeo.comment.Comment and
    * User (instead of many-to-one). This causes an exception if we try to add a aiss.videominer.models.vimeo.comment.Comment to the DataBase that has been
    * created by a User that already has a aiss.videominer.models.vimeo.comment.Comment in a previously stored Video. To avoid this exception, we automatically
    * assign an id to each new User with AutoIncrement.
     */
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_link")
    private String user_link;

    @JsonProperty("picture_link")
    private String picture_link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_link() {
        return user_link;
    }

    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public User(){

    }
    public User(String id,String name, String user_link, String picture_link ){
        this.id = id;
        this.name = name;
        this.user_link = user_link;
        this.picture_link = picture_link;
        
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user_link='" + user_link + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }

}
