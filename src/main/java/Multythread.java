public class Multythread {
public static Object monitor = new Object();
public static final int repetitionsQuantity = 5;
public static volatile char lastLetter = 'C';

    public static void main(String[] args) {
        LetterPrinterThread threadA = new LetterPrinterThread('C', 'A');
        LetterPrinterThread threadB = new LetterPrinterThread('A', 'B');
        LetterPrinterThread threadC = new LetterPrinterThread('B', 'C');

        threadA.start();
        threadB.start();
        threadC.start();



    }


    public static class LetterPrinterThread  extends Thread{
        private char before;
        private char after;


        public LetterPrinterThread (char after, char before) {
            this.before = before;
            this.after = after;

        }

        @Override
        public void run(){
            try{
                for (int i = 0; i < repetitionsQuantity; i++) {
                    synchronized (monitor){
                        while (lastLetter != before){
                            monitor.wait();
                        }
                        System.out.print(after);
                        lastLetter = after;
                        monitor.notifyAll();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


