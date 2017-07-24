public class Merge {
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // use java -ea to enable in runtime of assert
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                aux[k] = a[j++];
            else if (j > hi)            aux[k] = a[i++];
            else if (less(a[j], a[i])   aux[k] = a[j++];
            else                        aux[k] = a[i++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1)  {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort (aux, a, lo, mid);
        sort (aux, a, mid+1, hi);
        if (!less(a[mid+1), a[mid]) return;
        merge(a, aux, lo, hi);
    }

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }
}
