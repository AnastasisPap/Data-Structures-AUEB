public class Task {
    private int id;
    private int time;

    public Task(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}