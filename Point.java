
import java.util.ArrayList;
import java.util.List;

public class Point { //user-defined datatype Point(x,y)
    private double x;
    private double y;

    // Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter for X coordinate
    public double getX() {
        return x;
    }

    // Setter for X coordinate
    public void setX(double x) {
        this.x = x;
    }

    // Getter for Y coordinate
    public double getY() {
        return y;
    }

    // Setter for Y coordinate
    public void setY(double y) {
        this.y = y;
    }

}

    // Merge Sort for an array of points based on x-coordinate in descending order
    public static void mergeSort(Point[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Point[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Point[] leftArr = new Point[n1];
        Point[] rightArr = new Point[n2];

        // Copy data to temp arrays leftArr[] and rightArr[]
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays back into arr[]
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].getX() >= rightArr[j].getX()) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of leftArr[], if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy the remaining elements of rightArr[], if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int [] n={10,100,1000,10000,100000,1000000,10000000,99999998};//values of n for which code is being tested
        
        for(int c=0;c<8;++c){
            Point[] points = new Point[n[c]];//size of array determined by value of n passed

            //test values
            /* Point[] points = new Point[]{
            new Point(453, 726),
            new Point(827, 314),
            new Point(642, 132),
            new Point(219, 589),
            new Point(781, 943),
            new Point(367, 475),
            new Point(598, 861),
            new Point(154, 267),
            new Point(912, 58),
            new Point(321, 899)
            };*/

            // Generating random points and storing them in the array
            for (int i = 0; i < n[c]; i++) { //size of array determined by value of n passed
                double x = Math.random() * 1000; // Random x coordinate between 0 and 1000
                double y = Math.random() * 1000; // Random y coordinate between 0 and 1000
                points[i] = new Point(x, y);
            }

            long start = System.nanoTime();//start time
            
            // Sorting the points based on x-coordinate in descending order using merge sort
            mergeSort(points, 0, points.length - 1);

            // Display the sorted points
            /*System.out.println("Sorted Points (Descending Order based on X-coordinate):");
            for (int i = 0; i < n[c]; i++) {
            System.out.println("Point " + (i + 1) + ": " + points[i]);
            }*/

            List<Point> POpoints = new ArrayList<>();
            double currMaxY = Double.NEGATIVE_INFINITY;
            
            for (Point p: points) { //checking if given point is pareto-optimal or not
                if (p.getY() > currMaxY) {
                POpoints.add(p);  //if the point is Pareto optimal it is added to the list of points
                currMaxY = p.getY();
                }
            }
            long end = System.nanoTime();//end time
            System.out.println("for n=" + n[c]);
            System.out.println("Elapsed Time in nano seconds: "+ (end-start)); //total time elapsed for current value of n

           // Display the PO points
           /* System.out.println("Pareto Optimal Points:");
            for (Point p: POpoints) {
                System.out.println(p.getX()+","+p.getY());
            }*/
        }        
    }
}
