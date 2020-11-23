package iodemo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IODemo {
    private final static String dir = "/Users/lvbing/test/";

    public static void main(String[] args) throws IOException {
        File file = new File(dir + "test");

//        writeByStream(file);
//        System.out.println(readByStream(file));

//        writeByBufferStream(file);
//        System.out.println(readByBufferStream(file));
    }

    private static void writeByStream(File file) throws IOException {
        OutputStream os = new FileOutputStream(file, true);
        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        os.write(string.getBytes());
        os.close();
    }

    private static String readByStream(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        // 一次性取多少个字节
        byte[] bytes = new byte[1024];

        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();

        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;

        while ((length = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }

        in.close();

        return sb.toString();

    }

    private static void writeByBufferStream(File file) throws IOException {
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file, true));
        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        os.write(string.getBytes());
        os.close();
    }

    private static String readByBufferStream(File file) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        // 一次性取多少个字节
        byte[] bytes = new byte[1024];

        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();

        // 读取到的字节数组长度，为-1时表示没有数据
        int length = 0;

        while ((length = in.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, length));
        }

        in.close();

        return sb.toString();

    }

    private static void writeByStreamWriter(File file) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true));

        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        writer.write(string);
        writer.close();
    }

    private static String readByStreamReader(File file) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        char[] chars = new char[1024];

        StringBuilder sb = new StringBuilder();

        int length = 0;

        while ((length = isr.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }
        isr.close();

        return sb.toString();
    }

    private static void writeByWriter(File file) throws IOException {
        FileWriter fw = new FileWriter(file, true);

        String s = "松下问童子，言师采药去。只在此山中，云深不知处。";
        fw.write(s);
        fw.close();
    }

    private static String readByReader(File file) throws IOException {
        FileReader fr = new FileReader(file);
        // 一次性取多少个字节
        char[] chars = new char[1024];
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();
        // 读取到的字节数组长度，为-1时表示没有数据
        int length;
        // 循环取数据
        while ((length = fr.read(chars)) != -1) {
            // 将读取的内容转换成字符串
            sb.append(chars, 0, length);
        }
        // 关闭流
        fr.close();

        return sb.toString();
    }

    private static void writeByBufferWriter(File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        // 要写入的字符串
        String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
        bw.write(string);
        bw.close();
    }

    private static String readByBufferReader(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }






    /**
     * 字节流转字符流
     *
     * @param file
     * @throws IOException
     */
    private static void inputStreamReader(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        Reader reader = new InputStreamReader(inputStream);

        char[] charArray = new char[(int) file.length()];

        int size = reader.read(charArray);

        inputStream.close();
        reader.close();
    }

    private static void outputStreamWriter(File file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(outputStream);

        writer.write("");

        writer.close();
        outputStream.close();
    }
}
