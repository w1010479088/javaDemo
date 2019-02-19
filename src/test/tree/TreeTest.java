package test.tree;

public class TreeTest {
    public static class MinHeap {
        private int[] data;

        public MinHeap(int[] data) {
            this.data = data;
            buildHeap();
        }

        private void buildHeap() {
            for (int i = (data.length) / 2 - 1; i >= 0; i--) {
                heapify(i);
            }
        }

        private void heapify(int i) {
            int l = left(i);
            int r = right(i);
            int smallest = i;
            if (l < data.length && data[l] < data[i])
                smallest = l;
            if (r < data.length && data[r] < data[smallest])
                smallest = r;
            if (i == smallest)
                return;
            swap(i, smallest);
            heapify(smallest);
        }

        private int right(int i) {
            return (i + 1) << 1;
        }

        private int left(int i) {
            return ((i + 1) << 1) - 1;
        }

        private void swap(int i, int j) {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }

        public int getRoot() {
            return data[0];
        }

        public void setRoot(int root) {
            data[0] = root;
            heapify(0);
        }
    }

    public static class TopK {
        public static void main(String[] args) {
            int[] data = {56, 275, 12, 6, 45, 478, 41, 1236, 456, 12, 546, 45};
            int[] top5 = topK(data, 5);
            for (int i = 0; i < 5; i++) {
                System.out.println(top5[i]);
            }
        }

        private static int[] topK(int[] data, int k) {
            int[] topk = new int[k];
            for (int i = 0; i < k; i++) {
                topk[i] = data[i];
            }
            MinHeap heap = new MinHeap(topk);

            for (int i = k; i < data.length; i++) {
                int root = heap.getRoot();
                if (data[i] > root) {
                    heap.setRoot(data[i]);
                }
            }
            return topk;
        }
    }
}
