import ICEBucket.HelperServerPrx;
import ICEBucket.PrimaryServerPrx;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.io.FileWriter;
import java.io.IOException;

public class SortingQuery {

    public <T> CompletableFuture<Void> sortFileAsync(List<T> data, Comparator<T> comparator, HelperServerPrx helperServer) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        CompletableFuture<Void> processFuture = CompletableFuture.runAsync(() -> {
            try {
                List<List<T>> chunks = divideDataset(data, 10); // Assuming 10 chunks
                for (int i = 0; i < chunks.size(); i++) {
                    List<T> chunk = chunks.get(i);
                    List<T> sortedChunk = Bucket.sort(chunk, comparator, helperServer);
                    saveSortedChunkToFile(sortedChunk, fileName, i);
                }
                future.complete(null);
            } catch (IOException e) {
                e.printStackTrace();
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    private <T> List<List<T>> divideDataset(List<T> dataset, int numFiles) {
        int n = dataset.size();
        int chunkSize = n / numFiles;
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < n; i += chunkSize) {
            List<T> chunk = dataset.subList(i, Math.min(n, i + chunkSize));
            chunks.add(chunk);
        }
        return chunks;
    }

    private <T> void saveSortedChunkToFile(List<T> chunk, String originalFileName, int chunkIndex) {
        String outputFileName = "sorted_chunk_" + chunkIndex + "_" + originalFileName;
        try (FileWriter writer = new FileWriter(outputFileName)) {
            for (T element : chunk) {
                writer.write(element.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
