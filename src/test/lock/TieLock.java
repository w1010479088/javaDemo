package test.lock;

//死锁产生的原因:两个线程试图通过不同的顺序获得多个相同的锁.
// 如果请求锁的顺序相同,就不会出现循环的锁依赖现象,也就不会产生死锁了,一定要保证锁的顺序性.
public class TieLock {
    private static final Object tieLock = new Object();

    //错误的锁方式
    public void transferMoneyError(Account fromAcct, Account toAcct, DollarAmount amount) throws InsufficientFundsException {
        synchronized (fromAcct) {
            synchronized (toAcct) {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
    }

    //正确的锁方式
    public void transferMoney(final Account fromAcct, final Account toAcct, final DollarAmount amount) throws InsufficientFundsException {
        class Helper {
            private void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) > 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        int fromHash = hash(fromAcct);
        int toHash = hash(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    private int hash(Object obj) {
        return System.identityHashCode(obj);
    }
}

class Account {
    private DollarAmount amount;

    DollarAmount getBalance() {
        return amount;
    }

    void debit(DollarAmount amount) {

    }

    void credit(DollarAmount amount) {

    }
}

class DollarAmount implements Comparable<DollarAmount> {
    private int amount;

    private int amount() {
        return amount;
    }

    @Override
    public int compareTo(DollarAmount amount) {
        return this.amount - amount.amount();
    }
}

class InsufficientFundsException extends Exception {

}
