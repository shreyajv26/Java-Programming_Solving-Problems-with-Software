import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        //Initialize a counter numPoints = 0
        int numPoints = 0;
        // For each point in the shape
        for (Point P : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double AverageLength = 0.0;
        //Call Perimeter Method to get the perimeter of the shape
        double Perimeter = getPerimeter(s);
        //Call numPoints Method to get total number of points in the shape
        int numPoints = getNumPoints(s);
        AverageLength = Perimeter/numPoints;
        return AverageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with LargestSide = 0
        double LargestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt to currPt 
            double currDist = prevPt.distance(currPt);
            // Update LargestSide by currDist if currDist > LargestSide
            if (currDist > LargestSide){
                LargestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double LargestX = 0.0;
        for (Point P : s.getPoints()) {
            //Get the X-coordinate of the first point
            double X_coordinate = (double)P.getX();
            //Update LargestX if X_coordinate > LargestX
            if(X_coordinate > LargestX){
                LargestX = X_coordinate;
        }
    }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double LargestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            //Convert the file into a file resource for each file f
            FileResource fr = new FileResource(f);
            //Get points from the file resource
            Shape s = new Shape(fr);
            for (Point p : s.getPoints()) {
            }
            double peri = getPerimeter(s);
            if(peri > LargestPerimeter){
                LargestPerimeter = peri;
        }
    }
        return LargestPerimeter;
}

    public String getFileWithLargestPerimeter() {
        // Put code here
        double LargestPerimeter = 0.0;
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            //Convert the file into a file resource for each file f
            FileResource fr = new FileResource(f);
            //Get points from the file resource
            Shape s = new Shape(fr);
            for (Point p : s.getPoints()) {
            }
            double peri = getPerimeter(s);
            if(peri > LargestPerimeter){
                LargestPerimeter = peri;
                temp = f;
        }
    }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        //To print number of points
        System.out.println("Number of points are:");
        System.out.println(getNumPoints(s));
        //To print average length
        System.out.println("Average Length is:");
        System.out.println(getAverageLength(s));
        //To print the largest side
        System.out.println("Largest Side is:");
        System.out.println(getLargestSide(s));
        //To print the largest X
        System.out.println("Largest X is:");
        System.out.println(getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
