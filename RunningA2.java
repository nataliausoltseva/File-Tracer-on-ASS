/*
 * The class that runs the main A2 class
 */

public class RunningA2 implements Runnable {
    public void run() {
        A2 assignment2 = new A2();
    }
    // The main method that used invokeLater
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new RunningA2());
    }
}