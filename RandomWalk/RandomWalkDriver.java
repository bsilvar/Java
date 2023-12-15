import java.util.Scanner;

public class RandomWalkDriver {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter grid size: ");
        int gridSize = scan.nextInt();
        while(gridSize < 1) {
            System.out.println("Error: grid size must be positive!");
            System.out.print("Enter grid size: ");
            gridSize = scan.nextInt();
        }

        System.out.print("Enter random seed (0 for no seed): ");
        long seed = scan.nextLong();
        while(seed < 0) {
            System.out.println("Error: random seed must be >= 0!");
            System.out.print("Enter random seed (0 for no seed): ");
            seed = scan.nextLong();
        }

        RandomWalk walk;
        if(seed == 0) {
            walk = new RandomWalk(gridSize);
        } else {
            walk = new RandomWalk(gridSize, seed);
        }
        walk.createWalk();
        System.out.println(walk.toString());
        scan.close();

    }

}

