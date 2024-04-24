import sorting.*;
import Ice.*;
import java.util.List;

public class HelperServer implements sorting.HelperServer {

    @Override
    public List<T> mergeSort(List<T> data, Comparator<T> comparator) {
        if (data.size() <= 1) {
            return data;
        }

        int middle = data.size() / 2;
        List<T> left = data.subList(0, middle);
        List<T> right = data.subList(middle, data.size());

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }

    private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged;
    }
    


}
