import java.util.UUID;

class GenUUID {

    public void run() {
        UUID id = UUID.randomUUID();
        System.out.println(id);
    }
    public static void main(String[] args) {
        (new GenUUID()).run();
    }
}