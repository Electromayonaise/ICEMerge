import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import Ice.*;

public class Bucket {

    public static <T> CompletableFuture<List<T>> sort(List<T> data, Comparator<T> comparator, HelperServerPrx helperServer) {
       
        if (data.isEmpty()) {
            return CompletableFuture.completedFuture(data);
        }

        // Calculate number of buckets
        int numBuckets = (int) Math.sqrt(data.size());
        List<List<T>> buckets = new ArrayList<>(numBuckets);

        // Initialize buckets
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // Find min and max elements
        T min = data.stream().min(comparator).orElse(null);
        T max = data.stream().max(comparator).orElse(null);

        // Distribute elements into buckets
        for (T element : data) {
            int index = calculateBucketIndex(element, min, max, numBuckets, comparator);
            buckets.get(index).add(element);
        }

        // Sort each bucket using helper server
        List<CompletableFuture<List<T>>> sortedBucketFutures = new ArrayList<>();
        for (List<T> bucket : buckets) {
            sortedBucketFutures.add(helperServer.mergeSort(bucket, comparator));
        }

        // Combine sorted buckets
        return CompletableFuture.allOf(sortedBucketFutures.toArray(new CompletableFuture[0]))
            .thenApply(__ -> {
                List<T> sortedData = new ArrayList<>();
                sortedBucketFutures.forEach(future -> future.join().forEach(sortedData::add));
                return sortedData;
            });
    }

    private static <T> int calculateBucketIndex(T element, T min, T max, int numBuckets, Comparator<T> comparator) {
        double range = comparator.compare(max, min);
        double relativePosition = comparator.compare(element, min) - comparator.compare(min, max);
        return (int) (numBuckets * relativePosition / range);
    }
}
