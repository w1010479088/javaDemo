package test.design.template;

public abstract class ICalculator {
    public int calculate(String exp, String opt) {
        int[] nums = split(exp, opt);
        return calculate(nums[0], nums[1]);
    }

    public abstract int calculate(int first, int second);

    private int[] split(String exp, String opt) {
        String[] nums = exp.split(opt);
        int numFirst = Integer.parseInt(nums[0]);
        int numSecond = Integer.parseInt(nums[1]);
        return new int[]{numFirst, numSecond};
    }
}
