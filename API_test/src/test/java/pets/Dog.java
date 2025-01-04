package pets;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dog {

    private int id;
    private String name;
    private Category category;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    // Constructor, getters y setters
    public Dog(int id, String name, Category category, List<String> photoUrls, List<Tag> tags, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public static Dog generateRandomDog(int petId) {
        Random rand = new Random();
        String name = "Dog" + rand.nextInt(100);
        Category category = new Category(1, "Dogs");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("https://randomurl.com/photo" + rand.nextInt(100));
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(rand.nextInt(10), "Tag" + rand.nextInt(10)));
        String status = rand.nextBoolean() ? "available" : "sold";
        return new Dog(petId, name, category, photoUrls, tags, status);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}

