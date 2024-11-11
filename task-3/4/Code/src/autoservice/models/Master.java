package autoservice.models;

public class Master {
    private String name;
    private boolean isAvailable;

    public Master(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}