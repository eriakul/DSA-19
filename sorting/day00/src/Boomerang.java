import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int total = 0;
        for (int [] point:points){
            Map dist_dict = new HashMap();
            for (int [] point2:points){
                if (! point.equals(point2)){
                    double dist = calc_dist(point, point2);
                    if (dist_dict.containsKey(dist)){
                        int temp_dist = (int) dist_dict.get(dist);
                        dist_dict.put(dist, temp_dist+1) ;
                    }
                    else{
                        dist_dict.put(dist, 1);
                    }
                }
            }
            total += calc_total(dist_dict);
        }

        return total;
    }
    private static double calc_dist(int [] p1, int [] p2){
        return (Math.pow((double)p1[0]-p2[0],2) + (Math.pow((double)p1[1]-p2[1],2)));
    }

    private static int calc_total(Map dist_dict){
        int total = 0;
        Iterator<Map.Entry<Double, Integer>> iterator = dist_dict.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            if ((int)entry.getValue() > 1) {
                total += factorial((int)entry.getValue())/factorial((int)entry.getValue()-2);
            }
        }
        return total;
    }

    static int factorial(int n)
    {
        if (n == 0)
            return 1;

        return n*factorial(n-1);
    }

}

