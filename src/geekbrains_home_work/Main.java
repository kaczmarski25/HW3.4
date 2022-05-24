package geekbrains_home_work;



public class Main {
        static volatile char c = 'A';
        static Object monitor = new Object();

        static class ABC implements Runnable {
            private char first;
            private char second;

            public ABC (char first, char second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        try {
                            while (c != first)
                                monitor.wait();
                            System.out.print(first);
                            c = second;
                            monitor.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        public static void main(String[] args) {
            new Thread(new ABC('A','B')).start();
            new Thread(new ABC('B','C')).start();
            new Thread(new ABC('C','A')).start();
        }
}

