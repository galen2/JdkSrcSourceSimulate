package javallb.util.concurrent.lock;




public class ReentrantLock {

	 private final Sync sync = null;
	 
	 abstract static class Sync extends AbstractQueuedSynchronizer{
		 abstract void lock();
		 protected final boolean tryRelease(int releases) {
          int c = getState() - releases;
           if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
	 }
	 
	 static final class NonfairSync extends Sync{

		void lock() {
			if(compareAndSetState(0, 1)){//表示第一次获取锁
				setExclusiveOwnerThread(Thread.currentThread());
			}
			else
			{
				acquire(1);
			}
			
		}
		 protected final boolean tryAcquire(int acquires) {
	            return nonfairTryAcquire(acquires);
	      }
		 
		 protected final boolean nonfairTryAcquire(int acquires){
			 int state = getState();
			 Thread curent = Thread.currentThread();
			 if(state==0){
				 if(compareAndSetState(0,acquires)){
					 setExclusiveOwnerThread(Thread.currentThread());
				 }
				 return true;
			 }
			 else if(curent == getExclusiveOwnerThread()){//同一个线程多次重�?
				 state+=acquires;
				 setState(state);
				 return true;
			 }
			 return false;
		 }
	       
		 
		 
		 
	 }
}
