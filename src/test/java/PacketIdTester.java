public class PacketIdTester {
    private record HighLow(long high, long low) {}

    public static void main(String[] args) {

        test(317093744711721659L);
    }

    public static void test(long high, long low) {
        System.out.println((high << 32) | (low & 0xFFFFFFFFL));
    }

    public static void test(long id) {
        long high = id >>> 32;
        int low = (int) id;

        System.out.println(high + "," + low);
    }
}
