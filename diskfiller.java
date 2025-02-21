import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FillCDrive {

    // Set the target directory to the root of the C drive.
    private static final String TARGET_DIRECTORY = "C:\\";
    private static final String FILE_PREFIX = "filler";
    private static final int FILE_SIZE_MB = 1024; // Each file is 1GB by default.
    private static final int BUFFER_SIZE = 8 * 1024 * 1024; // 8MB buffer for faster writes.
    private static final int NUM_THREADS = 4; // Number of concurrent threads.
    
    private static final AtomicInteger fileCounter = new AtomicInteger(1);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        CompletionService<Void> completionService = new ExecutorCompletionService<>(executor);

        System.out.println("WARNING: This program will attempt to fill your C drive. Proceed ONLY if you understand the risks.");
        System.out.println("Starting to fill C drive...");

        try {
            while (true) {
                final int currentFileNumber = fileCounter.getAndIncrement();
                completionService.submit(() -> {
                    String fileName = TARGET_DIRECTORY + FILE_PREFIX + "_" + currentFileNumber + ".bin";
                    try {
                        writeFile(fileName, FILE_SIZE_MB);
                        System.out.println("Created file: " + fileName);
                    } catch (IOException e) {
                        // Propagate the exception to break out of the loop.
                        throw new RuntimeException("Error writing file " + fileName + ": " + e.getMessage(), e);
                    }
                    return null;
                });

                // Periodically wait for one task to complete to avoid overwhelming the system.
                if (currentFileNumber % (NUM_THREADS * 10) == 0) {
                    try {
                        Future<Void> future = completionService.take();
                        future.get();
                    } catch (ExecutionException e) {
                        System.err.println("File creation failed: " + e.getCause().getMessage());
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        } finally {
            executor.shutdownNow();
        }
        System.out.println("Terminating: The C drive may be full or an error occurred.");
    }

    private static void writeFile(String fileName, int sizeInMB) throws IOException {
        // Create a reusable 8MB buffer filled with zeros.
        byte[] buffer = new byte[BUFFER_SIZE];
        int totalBytes = sizeInMB * 1024 * 1024;
        int writes = totalBytes / BUFFER_SIZE;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            for (int i = 0; i < writes; i++) {
                bos.write(buffer);
            }
            bos.flush();
        }
    }
}
