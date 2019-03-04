import java.util.*;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    private static List<Point> twoify(Building building){
        List temp = new ArrayList();
        temp.add(new Point(building.l, building.h));
        temp.add(new Point(building.r, 0));
        return temp;
    }

    private static List<Point> merge(List<Point> skyline1, List<Point> skyline2){
        List<Skyline.Point> skyline = new ArrayList<>();
        int current_skyline_height = 0;
        int current_1_height = 0;
        int current_2_height = 0;

        Set keys = new HashSet();

        Map skyline1Map = new HashMap();
        Map skyline2Map = new HashMap();

        for (Point point:skyline1){
            keys.add(point.x);
            skyline1Map.put(point.x, point.y);
        }
        for (Point point:skyline2){
            keys.add(point.x);
            skyline2Map.put(point.x, point.y);
        }

        TreeSet<Integer> Xs = new TreeSet();
        Xs.addAll(keys);
        int new_skyline_height;
        for (int x: Xs){
            if (skyline1Map.containsKey(x)){
                current_1_height = (int) skyline1Map.get(x);
            }
            if (skyline2Map.containsKey(x)){
                current_2_height = (int) skyline2Map.get(x);
            }

            if (current_1_height > current_2_height){
                new_skyline_height = current_1_height;
            }
            else{
                new_skyline_height = current_2_height;
            }
            if (current_skyline_height != new_skyline_height){
                skyline.add(new Point(x, new_skyline_height));
                current_skyline_height = new_skyline_height;
            }
        }

        return skyline;

    }



    public static List<Point> skyline(Building[] array) {
        if (array.length == 1){
            return twoify(array[0]);
        }
        Building[] left = Arrays.copyOfRange(array, 0, array.length/2 + array.length%2);
        Building[] right = Arrays.copyOfRange(array,array.length/2 + array.length%2, array.length);
        List<Point> sorted_left = skyline(left);
        List<Point> sorted_right = skyline(right);
        return merge(sorted_left, sorted_right);


    }
}
