import cn.hutool.core.util.ArrayUtil;
import com.google.common.base.Stopwatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyuxiao
 * @date 2021-07-22 14:50
 * @description
 */
public class MainSort {
    private static final Random RANDOM = new Random();
    private static final int DEBUG = 50;
    private static final int MIDDLE = 1000;
    private static final int LARGE = 10000;
    private static final int MEGA = 100000;
    /**
     * 展示数组的大小
     */
    private static final int AMOUNT = 20;
    private static int[] nums = null;

    /**
     * 初始化数组
     *
     * @param type 数组大小
     */
    public static void initNums(int type) {
        int len;
        if (type == 0) {
            len = MIDDLE;
        } else if (type == 1) {
            len = LARGE;
        } else if (type == 2) {
            len = MEGA;
        } else {
            len = DEBUG;
        }
        nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = RANDOM.nextInt(len + 1);
        }
        System.out.println("随机初始化数组：" + len + "个元素。");
    }

    /**
     * 通过反射调用所有排序的方法，并计时
     *
     * @param nums 数组
     * @return 统计结果
     * @throws InvocationTargetException 发射异常
     * @throws IllegalAccessException    传参异常
     * @see Detail
     */
    public static List<Detail> toSort(int[] nums) throws InvocationTargetException, IllegalAccessException {
        final List<Detail> details = new ArrayList<>();
        final Class<MySort> mySortClass = MySort.class;
        final Method[] declaredMethods = mySortClass.getDeclaredMethods();
        System.out.print("原始数组：");
        toDisplay(nums);
        for (Method m : declaredMethods) {
            if (!m.isAnnotationPresent(ToSort.class)) {
                continue;
            }
            int[] toSortNums = ArrayUtil.clone(nums);
            System.out.print("当前排序方法：" + m.getName());
            final Stopwatch watch = Stopwatch.createStarted();
            m.invoke(null, toSortNums);
            watch.stop();
            System.out.print("，排序成功？" + ArrayUtil.isSorted(toSortNums));
            System.out.printf("，执行时长：%d 秒. %d 毫秒. %n", watch.elapsed(TimeUnit.SECONDS), watch.elapsed(TimeUnit.MILLISECONDS));
            details.add(new Detail(m.getName(), watch.elapsed(TimeUnit.MILLISECONDS)));
            toDisplay(toSortNums);
        }
        return details;
    }

    /**
     * 展示数组，展示前amount个 + 后amount个
     *
     * @param nums 数组
     */
    public static void toDisplay(int[] nums) {
        if (nums.length <= 2 * AMOUNT) {
            System.out.println("nums = " + Arrays.toString(nums));
        } else {
            System.out.print("[");
            for (int i = 0; i < AMOUNT; i++) {
                System.out.printf("%s, ", nums[i]);
            }
            System.out.print("..., ");
            for (int i = nums.length - AMOUNT; i < nums.length - 1; i++) {
                System.out.printf("%s, ", nums[i]);
            }
            System.out.printf("%s]\n", nums[nums.length - 1]);
        }
    }

    /**
     * main入口，展示排序结果
     *
     * @param args main
     * @throws InvocationTargetException 反射异常
     * @throws IllegalAccessException    传参异常
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入数字，选择规模 ==> 0：middle；1：large；2：mega；3：debug；其它：退出");
            if (scanner.hasNextInt()) {
                final int i = scanner.nextInt();
                if (i != 0 && i != 1 && i != 2 && i != 3) {
                    System.out.println("Bye Bye!");
                    break;
                }
                initNums(i);
                final List<Detail> details = toSort(nums);
                details.sort((o1, o2) -> (int) (o1.time - o2.time));
                System.out.println(details);
            } else {
                System.out.println("Bye Bye!");
                break;
            }
            System.out.println("======================================================================");
        }
    }
}




