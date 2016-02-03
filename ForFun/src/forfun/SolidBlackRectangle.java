package forfun;

import java.util.ArrayList;
import java.util.List;

/*
 * Imagine we have an image where every pixel is white or black. We¡¯ll represent this image as a simple 2D array (0 = black, 1 = white). The image you get is known to have a single black rectangle on a white background.

Find this rectangle and return its coordinates.

Here¡¯s a sample ¡°image¡± using JavaScript (feel free to rewrite in your language of choice):

var image = [
	[1, 1, 1, 1, 1, 1, 1],
	[1, 1, 1, 1, 1, 1, 1],
	[1, 1, 1, 0, 0, 0, 1],
	[1, 1, 1, 0, 0, 0, 1],
	[1, 1, 1, 1, 1, 1, 1],
];
 */
public class SolidBlackRectangle {
	public List<Point> findCoordinates(int[][] image) {
		if (image == null || image.length == 0) {
			return new ArrayList<Point>();
		}

		ArrayList<Point> res = new ArrayList<Point>();
		for (int rowIdx = 0; rowIdx < image.length; rowIdx++) {
			for (int colIdx = 0; colIdx < image[rowIdx].length; colIdx++) {
				if (image[rowIdx][colIdx] == 1) {
					continue;
				}

				if (res.size() == 0) {
					Point p = new Point(rowIdx, colIdx);
					res.add(p);
					continue;
				}

				if (res.size() == 1) {
					Point p = new Point(rowIdx, colIdx);
					res.add(p);
					continue;
				} else {
					Point p = res.get(1);
					p.x = Math.max(p.x, rowIdx);
					p.y = Math.max(p.y, colIdx);
				}
			}
		}

		// double check all the points in the area is valid 0
		if (res.size() == 2) {
			Point start = res.get(0);
			Point end = res.get(1);

			for (int rowIdx = start.x; rowIdx <= end.x; rowIdx++) {
				for (int colIdx = start.y; colIdx <= end.y; colIdx++) {
					if (image[rowIdx][colIdx] == 1) {
						return new ArrayList<Point>();
					}
				}
			}
		}

		return res;
	}

	public String printCoordinates(List<Point> points) {
		StringBuilder sb = new StringBuilder();
		if (points == null || points.size() == 0) {
			return "No valid Rectangle found!";
		}

		sb.append("Rectangle found\n");

		if (points.size() == 1) {
			sb.append("Rectangle start x: " + points.get(0).x);
			sb.append("Rectangle end x: " + points.get(0).x);
			sb.append("Rectangle start y: " + points.get(0).y);
			sb.append("Rectangle start y: " + points.get(0).y);
		} else {
			sb.append("Rectangle start x: " + points.get(0).x + " ");
			sb.append("Rectangle end x: " + points.get(1).x + " ");
			sb.append("Rectangle start y: " + points.get(0).y + " ");
			sb.append("Rectangle start y: " + points.get(1).y + " ");
		}

		return sb.toString().trim();
	}

	public static void main(String[] args) {
		SolidBlackRectangle test = new SolidBlackRectangle();

		int[][] image = new int[][] { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 } };

		System.out.println(test.printCoordinates(test.findCoordinates(image)));

		image = new int[][] { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1 } };

		System.out.println(test.printCoordinates(test.findCoordinates(image)));
	}
}

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
