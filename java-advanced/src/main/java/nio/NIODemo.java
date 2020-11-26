package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class NIODemo {
    private final static String SRC_FILE_NAME = "/Users/lvbing/test/test1";
    private final static String DEST_FILE_NAME = "/Users/lvbing/test/test2";

    public static void main(String[] args) throws IOException {
        copyFileByNIO(SRC_FILE_NAME, DEST_FILE_NAME);
    }

    /**
     * 用NIO从源文件复制数据到目标文件
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    private static void copyFileByNIO(String srcFile, String destFile) throws IOException {

        try (FileInputStream fi = new FileInputStream(srcFile);
             FileOutputStream fo = new FileOutputStream(destFile)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            try (FileChannel inChannel = fi.getChannel();
                 FileChannel outChannel = fo.getChannel()) {
                while (inChannel.read(buffer) != -1) {
                    buffer.flip();

                    outChannel.write(buffer);

                    buffer.clear();
                }
            }
        }
    }
}
