package SS6.EX6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector implements Runnable {

    private ThreadMXBean bean =
            ManagementFactory.getThreadMXBean();

    @Override
    public void run() {

        System.out.println("Đang quét deadlock...");

        long[] threadIds = bean.findDeadlockedThreads();

        if (threadIds != null) {
            System.out.println("Phát hiện DEADLOCK!");
        } else {
            System.out.println("Không phát hiện deadlock.");
        }
    }
}