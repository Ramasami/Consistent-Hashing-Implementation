package distributed.systems.consistent.hashing;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.*;

public class ConsistentHashing {

    private final int searchSpace;
    private final int pointMultiplier;
    private final ConcurrentMap<Node, List<Long>> nodePositions;
    private final ConcurrentSkipListMap<Long, Node> nodeMappings;
    private final Function<String, Long> hashFunction;
    private int n = 0;


    public ConsistentHashing(int searchSpace, int pointMultiplier, Function<String, Long> hashFunction) {
        this.searchSpace = searchSpace;
        this.pointMultiplier = pointMultiplier;
        this.nodePositions = new ConcurrentHashMap<>();
        this.nodeMappings = new ConcurrentSkipListMap<>();
        this.hashFunction = hashFunction;
    }

    public void addNode(Node node) {
        nodePositions.put(node, new CopyOnWriteArrayList<>());
        for (int i = 0; i < pointMultiplier; i++) {
            for (int j = 0; j < node.getWeight(); j++) {
                final Long point = hashFunction.apply((i * pointMultiplier + j) + node.getId()) % searchSpace;
                nodePositions.get(node).add(point);
                nodeMappings.put(point, node);
            }
        }
    }

    public void removeNode(Node node) {
        for (final Long point : nodePositions.remove(node)) {
            nodeMappings.remove(point);
        }
    }

    public Node getAssignedNode(String request) {
        final Long key = hashFunction.apply(request);
        final Map.Entry<Long, Node> entry = nodeMappings.higherEntry(key);
        if (entry == null) {
            return nodeMappings.firstEntry().getValue();
        } else {
            return entry.getValue();
        }
    }
}
