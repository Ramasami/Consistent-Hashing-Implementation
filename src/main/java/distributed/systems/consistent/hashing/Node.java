package distributed.systems.consistent.hashing;

import java.io.Serializable;

public class Node implements Serializable {

    private String ip;
    private String id;
    private int weight;

    public Node(String ip, String id, int weight) {
        this.ip = ip;
        this.id = id;
        this.weight = weight;
    }

    public Node(String ip, String id) {
        this.ip = ip;
        this.id = id;
        this.weight = 1;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "ip='" + ip + '\'' +
                ", id='" + id + '\'' +
                ", weight=" + weight +
                '}';
    }
}
