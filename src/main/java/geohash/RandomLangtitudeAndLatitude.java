package geohash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligc on 2021/1/5 20:43
 */
public class RandomLangtitudeAndLatitude {
    private int count;
    private Map<Double, Double> map;

    public RandomLangtitudeAndLatitude(int count){
        this.count = count;
        this.map = new HashMap<Double, Double>(count);
        init();
    }

    private void init(){
        for(int i = 0 ; i < count; i ++){
            double longtitude = 115 + (int)(Math.random() * 1000) * 0.01 + (int)(Math.random() * 2);
            double latitude = 39 + (int)(Math.random() * 1000) * 0.01 + (int)(Math.random() * 2);
            map.put(longtitude, latitude);
        }
        this.count = map.size();
    }

    public Map<Double, Double> getMap(){
        return this.map;
    }

    public int size(){
        return this.count;
    }
}
