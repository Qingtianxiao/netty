package jvm.thread;

/**
 * Created by ligc on 2021/2/18 13:38
 */
//extends，继承自Runnable，Runnable及其子类咯
public interface ThreadPool <Job extends Runnable>{
    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
