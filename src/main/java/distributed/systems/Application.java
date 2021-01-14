package distributed.systems;

import distributed.systems.consistent.hashing.ConsistentHashing;
import distributed.systems.consistent.hashing.Node;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        ConsistentHashing hashing = new ConsistentHashing(100, 3, x-> (long) x.hashCode());
        int t;
        Scanner sc = new Scanner(System.in);
        System.out.print("queries: ");
        t = sc.nextInt();
        while(t-->0) {
            System.out.print("action (0: string, 1: add node, 2: remove node) (string)|(ip id wt)|(ip id wt): ");
            int type = sc.nextInt();
            if(type == 0) {
                System.out.println(hashing.getAssignedNode(sc.nextLine()));
            } else if(type == 1){
                String ip = sc.next();
                String id= sc.next();
                int wt = sc.nextInt();
                sc.nextLine();
                Node n = new Node(ip,id,wt);
                hashing.addNode(n);
            } else {
                String ip = sc.next();
                String id= sc.next();
                int wt = sc.nextInt();
                sc.nextLine();
                Node n = new Node(ip,id,wt);
                hashing.removeNode(n);
            }
        }
    }
}
