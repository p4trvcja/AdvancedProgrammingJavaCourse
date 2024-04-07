public class Main {
    public static void main(String[] args) {
        DownloadExample d = new DownloadExample();
        d.sequentialDownload();
        d.concurrentDownload2();
        d.concurrentDownload3();
    }
}