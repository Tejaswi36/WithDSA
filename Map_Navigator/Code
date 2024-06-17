import java.util.*;
class Location {
    int x;
    int y;
    String name;
  
    public Location(int x, int y, String name) {
      this.x = x;
      this.y = y;
      this.name = name;
    }
  }
  
  class Edge {
    Location destination;
    int distance;
  
    public Edge(Location destination, int distance) {
      this.destination = destination;
      this.distance = distance;
    }
  }
  
  class Graph {
    List<Location> locations;
    Map<Location, List<Edge>> edges; // Map location to its connected edges
  
    public Graph() {
      locations = new ArrayList<>();
      edges = new HashMap<>();
    }
  
    public void addLocation(Location location) {
      locations.add(location);
      edges.put(location, new ArrayList<>());
    }
  
    public void addEdge(Location from, Location to, int distance) {
      edges.get(from).add(new Edge(to, distance));
    }
  
    public List<Location> findShortestPath(Location start, Location end) {
      // Priority queue to store distances and corresponding locations
      PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
  
      // Map to store distances from the starting location to each other location
      Map<Location, Integer> distances = new HashMap<>();
  
      // Map to store parent node for path reconstruction
      Map<Location, Location> parent = new HashMap<>();
  
      // Initialize distances and parent for all locations
      for (Location location : locations) {
        distances.put(location, Integer.MAX_VALUE);
        parent.put(location, null);
      }
  
      distances.put(start, 0);
      pq.add(new Node(start, 0));
  
      while (!pq.isEmpty()) {
        Location current = pq.poll().location;
  
        // If already reached the destination, break
        if (current == end) {
          break;
        }
  
        // Relax edges for all neighbors
        for (Edge edge : edges.get(current)) {
          Location neighbor = edge.destination;
          int newDistance = distances.get(current) + edge.distance;
  
          if (distances.get(neighbor) > newDistance) {
            distances.put(neighbor, newDistance);
            parent.put(neighbor, current);
            pq.add(new Node(neighbor, newDistance));
          }
        }
      }
  
      // Reconstruct path from end to start using parent pointers
      List<Location> path = new ArrayList<>();
      Location current = end;
      while (current != null) {
        path.add(0, current);
        current = parent.get(current);
      }
  
      return path;
    }
  }
  
  class Node {
    Location location;
    int distance;
  
    public Node(Location location, int distance) {
      this.location = location;
      this.distance = distance;
    }
  }
  
  class Navigator {
    private Graph graph;
  
    public Navigator(Graph graph) {
      this.graph = graph;
    }
  
    public List<String> getDirections(Location start, Location end) {
      List<Location> path = graph.findShortestPath(start, end);
      List<String> directions = new ArrayList<>();
  
      for (int i = 0; i < path.size() - 1; i++) {
        Location current = path.get(i);
        Location next = path.get(i + 1);
  
        // Simple direction based on x and y coordinates (replace for actual directions)
        String direction = "";
        if (next.x > current.x) {
          direction = "Move East";
        } else if (next.x < current.x) {
          direction = "Move West";
        } else if (next.y > current.y) {
          direction = "Move North";
        } else {
          direction = "Move South";
        }
        directions.add(direction + " from " + current.name + " to " + next.name);
      }
  
      return directions;
    }
  }
  
  public class Main {
    public static void main(String[] args) {
      Graph map = new Graph();
  
      // Add locations
      Location location1 = new Location(10, 20, "Library");
      Location location2 = new Location(30, 15, "Cafeteria");
      Location location3 = new Location(20, 35, "Computer Lab");
      Location location4 = new Location(40, 25, "Gym");
      map.addLocation(location1);
      map.addLocation(location2);
      map.addLocation(location3);
      map.addLocation(location4);
  
      // Add edges (distances between locations)
      map.addEdge(location1, location2, 10); // Distance between Library and Cafeteria (10 units)
      map.addEdge(location1, location3, 15); // Distance between Library and Computer Lab (15 units)
      map.addEdge(location2, location4, 20); // Distance between Cafeteria and Gym (20 units)
      map.addEdge(location3, location4, 12); // Distance between Computer Lab and Gym (12 units)
  
      // Example usage: Find directions from Library to Gym
      Location start = location1;
      Location end = location4;
      Navigator navigator = new Navigator(map);
      List<String> directions = navigator.getDirections(start, end);
  
      System.out.println("Directions from " + start.name + " to " + end.name + ":");
      for (String direction : directions) {
        System.out.println(direction);
      }
    }
  }
  
  
