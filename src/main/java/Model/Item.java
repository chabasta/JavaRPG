package Model;

public class Item {

    private boolean picked_up;

    public Item(boolean picked_up) {
        this.picked_up = picked_up;
    }

    public boolean isPicked_up() {
        return picked_up;
    }

    public void setPicked_up(boolean picked_up) {
        this.picked_up = picked_up;
    }
}
