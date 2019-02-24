import java.util.*;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Map comboMap = new HashMap();
        Set combos = new HashSet();
        for (int i = 0; i < arr.length; i++){
            if (comboMap.containsKey(sum-arr[i])){
                Set temp = (Set) comboMap.get(sum-arr[i]);
                Iterator setIterator = temp.iterator();
                while(setIterator.hasNext()){
                    Set newSet = (Set)setIterator.next();
                    if (! newSet.contains(i)){
                        newSet.add(i);
                        combos.add(newSet);
                    }
                }

            }
            for (int j = 0; j < arr.length; j++){
                if (j != i){
                    if (comboMap.containsKey(arr[j] + arr[i])){
                        Set temp = (Set) comboMap.get(arr[j] + arr[i]);
                        Set temp2 = new HashSet();
                        temp2.add(i);
                        temp2.add(j);
                        temp.add(temp2);
                        comboMap.replace(arr[j]+ arr[i], temp);
                    }
                    else{
                        Set temp = new HashSet();
                        Set temp2 = new HashSet();
                        temp2.add(i);
                        temp2.add(j);
                        temp.add(temp2);
                        comboMap.put(arr[j] + arr[i], temp);

                    }
                }
            }
        }
        System.out.println(combos);
        return combos.size();
    }
}
