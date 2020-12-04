package ksl.academic.algorithm.sim;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.base.Preconditions;

/**
 * https://www.interviewcake.com/question/java/find-duplicate-files?
 *
 * @author Kent
 */
public class FindDuplicate {

    public static void main(String[] args) throws Exception {
        Collection<FilePaths> result = findDuplicate(new File("C:\\workspace"));

        List<FilePaths> filtered = result.stream()
                .filter(x -> x.getDuplicatePath() != null)
                .collect(Collectors.toList());

        for (FilePaths f : filtered) {
            System.out.println(f);
        }
    }

    static public Collection<FilePaths> findDuplicate(File root) throws Exception {
        Preconditions.checkNotNull(root);
        Map<String, FilePaths> cache = new HashMap<>(1000);
        scanFolder(root, cache);
        return cache.values();
    }


    private static void scanFolder(File root, Map<String, FilePaths> cache) throws Exception {

        List<File> fileQ = new LinkedList<>();
        fileQ.addAll(Arrays.asList(root.listFiles()));

        while (!fileQ.isEmpty()) {
            File f = fileQ.remove(0);
            if (!f.isDirectory()) {
                String checksum = computeMd5(f);
                if (!cache.containsKey(checksum)) {
                    cache.put(checksum, new FilePaths(null, null));
                }
                cache.get(checksum).update(f);
            } else {
                fileQ.addAll(Arrays.asList(f.listFiles()));
            }
        }
    }


    private static String computeMd5(File f) throws Exception {
        return DigestUtils.md5Hex(new FileInputStream(f).readAllBytes());
    }

    static class FilePaths {

        private Path duplicatePath;
        private Path originalPath;

        public FilePaths(Path duplicatePath, Path originalPath) {
            this.duplicatePath = duplicatePath;
            this.originalPath = originalPath;
        }

        public void update(File f) throws Exception {
            if (originalPath == null) {
                originalPath = f.toPath();
            } else {
                long origTime = getCreationTime(originalPath);
                long fileTime = getCreationTime(f.toPath());
                if (fileTime < origTime) {
                    duplicatePath = originalPath;
                    originalPath = f.toPath();
                } else {
                    duplicatePath = f.toPath();
                }
            }

        }

        private long getCreationTime(Path path) throws Exception {
            // New method...don't expect to memorize
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return attr.creationTime().toMillis();
        }

        public Path getDuplicatePath() {
            return duplicatePath;
        }

        public Path getOriginalPath() {
            return originalPath;
        }

        @Override
        public String toString() {
            return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
        }
    }
}
