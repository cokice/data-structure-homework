class SearchResult {
    // 定义两个字段：index表示查找结果，count表示比较次数
    int index;
    int count;

    public SearchResult(int index, int count) {
        this.index = index;
        this.count = count;
    }
}
public class searchdemo {
    public static SearchResult sequentialSearch(int[] sequence, int number) {
        int count = 0;

        for (int i = 0; i < sequence.length; i++) {
            count++;
            if (sequence[i] == number) {
                return new SearchResult(i, count);
            }
        }
        return new SearchResult(-1, count);
    }

    public static SearchResult binarySearch(int[] sequence, int number, int left) {
        int right = sequence.length - 1;
        int count = 0;
        while (left <= right) {
            count++;
            int middle = (left + right) / 2;
            if (sequence[middle] == number) {
                return new SearchResult(middle, count);
            } else if (sequence[middle] < number) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return new SearchResult(-1, count);
    }




    public static void main(String[] args) {
        int[] sequence = {5, 11, 23, 35, 51, 64, 72, 85, 88, 90, 98};

        SearchResult result1 = sequentialSearch(sequence, 30);
        SearchResult result2 = sequentialSearch(sequence, 72);

        System.out.println("在" + result1.index +"位置处查找到了30"+" (比较了" + result1.count +"次)");
        System.out.println("在" + result2.index +"位置处查找到了72"+" (比较了" + result2.count +"次)");
        SearchResult result3 = binarySearch(sequence, 30,0);
        SearchResult result4 = binarySearch(sequence, 72,0);

        System.out.println("在" + result3.index +"位置处查找到了30"+" (比较了" + result3.count +"次)");
        System.out.println("在" + result4.index +"位置处查找到了72"+" (比较了" + result4.count +"次)");

    }
}
