package Endterm.Question2_c;

import java.util.Arrays;

public class Arrays1 {
    int[] array;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrays1 arrays1 = (Arrays1) o;
        return Arrays.equals(array, arrays1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
