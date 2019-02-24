import java.util.Arrays;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        locksAndKeysHelper(locks, keys, 0, locks.length-1);
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        return result;
    }

    static void printArray(String str, char[] array){
        System.out.print(str);
        for (char ch: array){
            int i = (int) ch;
            System.out.print(" "+i);
        }
        System.out.println();
    }
    static void locksAndKeysHelper(char[] locks, char[] keys, int startIndex, int endIndex) {
        System.out.print("Entering loop with:");
        System.out.println("Keys: "+ Arrays.toString(keys));
        System.out.println("Locks: "+ Arrays.toString(locks));
        printArray("Numeric Keys:", keys);
        printArray("Numeric Locks: ", locks);

        System.out.println("Start Index: "+ startIndex);
        System.out.println("End Index: "+ endIndex);
        if (startIndex < endIndex) {

            int keyValue = keys[startIndex];
            int pointerFront = startIndex;
            int pointerEnd = endIndex;
            while (pointerFront < pointerEnd) {
                while ((int) locks[pointerFront] < keyValue && pointerFront < pointerEnd) {
                    System.out.println("Creeping pointerFront forward...");
                    pointerFront++;
                }
                while ((int)locks[pointerEnd] > keyValue && pointerFront < pointerEnd) {
                    System.out.println("Creeping pointerBack backwards...");
                    pointerEnd--;
                }
                if (pointerFront < pointerEnd) {
                    System.out.println("Swapping!");
                    swap(locks, pointerFront, pointerEnd);
                    printArray("New locks: ", locks);
                }
            }

            char lockValue = locks[pointerEnd];
            pointerFront = startIndex;
            pointerEnd = endIndex;
            while (pointerFront < pointerEnd) {
                while (keys[pointerFront] < lockValue && pointerFront < pointerEnd) {
                    pointerFront++;
                }
                while (keys[pointerEnd] > lockValue && pointerFront < pointerEnd) {
                    pointerEnd--;
                }
                if (pointerFront < pointerEnd) {
                    swap(keys, pointerFront, pointerEnd);
                }
            }
            locksAndKeysHelper(locks, keys, startIndex, pointerEnd - 1);
            locksAndKeysHelper(locks, keys, pointerEnd + 1, endIndex);


        }

    }
}




