package test.enumm;

public enum ColorsEntity implements IColor {

    Green(2, 56, 89) {
        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public int getColorInt() {
            return 0;
        }
    },

    Red(2, 56, 89) {
        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public int getColorInt() {
            return 0;
        }
    },

    White(2, 56, 89) {
        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public int getColorInt() {
            return 0;
        }
    };

    private int first;
    private int second;
    private int third;

    ColorsEntity(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }
}
