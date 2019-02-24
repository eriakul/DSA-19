import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String [] arr = s.split(" ");
        Map<String,Integer> dict = new HashMap();
        for (String word: arr){
            if (dict.containsKey(word)){
                dict.put(word, (int)dict.get(word)+1);
            }
            else{
                dict.put(word, 1);
            }
        }
        Map<Integer, ArrayList> new_dict = new HashMap();
        for (Map.Entry<String,Integer> entry : dict.entrySet()) {
            if (!new_dict.containsKey(entry.getValue())) {
                ArrayList temp = new ArrayList();
                temp.add(entry.getKey());
                new_dict.put(entry.getValue(), temp);
            } else {
                ArrayList temp = new_dict.get(entry.getValue());
                temp.add(entry.getKey());
                new_dict.put(entry.getValue(), temp);
            }
        }
        ArrayList result = new ArrayList();
        for (int i = arr.length; i > 0; i--){
            if (new_dict.containsKey(i)){
                for (int j = 0; j<new_dict.get(i).size(); j++){
                    for (int k = 0; k < i; k++) {
                        result.add(new_dict.get(i).get(j));
                    }
                }
            }
        }
            String new_result = String.join(" ", result);
            System.out.println(new_result);
            return new_result;
    }

}
