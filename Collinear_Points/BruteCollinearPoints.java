/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    private int noLineSegements;
    private LineSegment[] ln;

    public BruteCollinearPoints(Point[] points) {
        noLineSegements = 0;
        ln = new LineSegment[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
                        for (int z = k + 1; z < points.length; z++){
                            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[z])) {
                                ln[noLineSegements++] = new LineSegment(points[0], points[z]);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return noLineSegements;
    }

    public LineSegment[] segments() {
        return ln;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
