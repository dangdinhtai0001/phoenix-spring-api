/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 10:13 AM
 */

package com.phoenix.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
    public static final char EXTENSION_SEPARATOR = '.';
    private static final int NOT_FOUND = -1;

    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * @param rootPath : đường dẫn gốc
     * @return danh sách tất cả các path của tất cả các tệp tin trong thư mục gốc
     * <p>
     * nếu không có hay gặp lỗi sẽ trả về 1 danh sach rỗng
     */
    public static List<String> getAllFilePathInDirectory(String rootPath) {
        try (Stream<Path> walk = Files.walk(Paths.get(rootPath))) {
            // We want to find only regular files

            return walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    /**
     * @param strPath : đường dẫn cần lấy tên file
     * @return tên file (có cả extension)
     * <p>
     * ví dụ: F:\PROJECT\fss-bca-cddl-data-test\BieumauA_CHUAN.xls --> BieumauA_CHUAN.xls
     */
    public static String getFileNameFromPath(String strPath) {
        // create object of Path
        Path path = Paths.get(strPath);

        // call getFileName() and get FileName path object
        return path.getFileName().toString();
    }

    /**
     * Gets the base name, minus the full path and extension, from a full fileName.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * The text after the last forward or backslash and before the last dot is returned.
     * <pre>
     * a/b/c.txt --&gt; c
     * a.txt     --&gt; a
     * a/b/c     --&gt; c
     * a/b/c/    --&gt; ""
     * </pre>
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param fileName the fileName to query, null returns null
     * @return the name of the file without the path, or an empty string if none exists.
     */
    public static String removeExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        requireNonNullChars(fileName);

        final int index = indexOfExtension(fileName);
        if (index == NOT_FOUND) {
            return fileName;
        }
        return fileName.substring(0, index);
    }


    /**
     * @param path : path cần lấy tên file
     * @return tên file không bao gồm extension
     */
    public static String getBasenameFromPath(String path) {
        return removeExtension(getFileNameFromPath(path));
    }

    /**
     * Checks the input for null bytes, a sign of unsanitized data being passed to to file level functions.
     * <p>
     * This may be used for poison byte attacks.
     *
     * @param path the path to check
     */
    private static void requireNonNullChars(String path) {
        if (path.indexOf(0) >= 0) {
            throw new IllegalArgumentException("Null byte present in file/path name. There are no "
                    + "known legitimate use cases for such data, but several injection attacks may use it");
        }
    }

    /**
     * Returns the index of the last directory separator character.
     * <p>
     * This method will handle a file in either Unix or Windows format.
     * The position of the last forward or backslash is returned.
     * <p>
     * The output will be the same irrespective of the machine that the code is running on.
     *
     * @param fileName the fileName to find the last path separator in, null returns -1
     * @return the index of the last separator character, or -1 if there
     * is no such character
     */
    public static int indexOfLastSeparator(final String fileName) {
        if (fileName == null) {
            return NOT_FOUND;
        }
        final int lastUnixPos = fileName.lastIndexOf(UNIX_SEPARATOR);
        final int lastWindowsPos = fileName.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    /**
     * Returns the index of the last extension separator character, which is a dot.
     * <p>
     * This method also checks that there is no directory separator after the last dot. To do this it uses
     * {@link #indexOfLastSeparator(String)} which will handle a file in either Unix or Windows format.
     * </p>
     * <p>
     * The output will be the same irrespective of the machine that the code is running on, with the
     * exception of a possible {@link IllegalArgumentException} on Windows (see below).
     * </p>
     * <b>Note:</b> This method used to have a hidden problem for names like "foo.exe:bar.txt".
     * In this case, the name wouldn't be the name of a file, but the identifier of an
     * alternate data stream (bar.txt) on the file foo.exe. The method used to return
     * ".txt" here, which would be misleading. Commons IO 2.7, and later versions, are throwing
     * an {@link IllegalArgumentException} for names like this.
     *
     * @param fileName the fileName to find the last extension separator in, null returns -1
     * @return the index of the last extension separator character, or -1 if there is no such character
     * @throws IllegalArgumentException <b>Windows only:</b> The fileName parameter is, in fact,
     *                                  the identifier of an Alternate Data Stream, for example "foo.exe:bar.txt".
     */
    public static int indexOfExtension(final String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            return NOT_FOUND;
        }

        final int extensionPos = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        final int lastSeparator = indexOfLastSeparator(fileName);
        return lastSeparator > extensionPos ? NOT_FOUND : extensionPos;
    }
}
