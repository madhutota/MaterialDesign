package bornbaby.materialdesign.model;

/**
 * Created by madhu on 11-Nov-17.
 */

public class Dog {
    private String breed;
    private String image;

    public Dog(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
