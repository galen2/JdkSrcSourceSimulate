package javallb.util.concurrent.lock;

import sun.misc.Unsafe;
import sunllb.misc.UnsafeUtil;
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3287591765528699372L;

	private transient volatile Node head;

    private transient volatile Node tail;

	private volatile int state;
	
	protected final void  setState(int newState){
		
		this.state = newState;
	}

	protected final int getState() {
        return state;
    }
	
	
	
	 
	 private final boolean parkAndCheckInterrupt() {
		 //LockSupport.park(this);
		 return true;
	 }
	private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
		int ws = pred.waitStatus;
		if(ws ==Node.SIGNAL){
			return true;
		}
		else
		{
			compareAndSetWaitStatus(pred, ws, Node.SIGNAL);			
		}
		return true;
	}
	
	public final void acquire(int arg){
		if(!tryAcquire(arg) &&
				acquireQueued(addWaiter(Node.EXCLUSIVE), arg)){
	        Thread.currentThread().interrupt();
		}
	}
	
	final boolean acquireQueued(final Node node, int arg) {
		for(;;){
			boolean interrupted = false;
			final Node pre = node.predecessor();
			Node h = head;
			if(h !=null && tryAcquire(arg)){
				setHead(node);
				pre.next = null;
				return interrupted;
			}
			
			if(shouldParkAfterFailedAcquire(pre,node)&&parkAndCheckInterrupt()){
				interrupted=true;
			}
		}
		
	}
	
	
	
	private Node addWaiter(Node mode){
		Thread curentThread = Thread.currentThread();
		Node node = new Node(curentThread, mode);
		
		Node prev = tail;
		if(prev !=null){
			if (compareAndSetTail(prev, node)) {
				prev.next = node;
                return node;
            }
		}
		
		enq(node);
		return node;
	}
	
	private Node enq(final Node node){
		for(;;){
			Node t = tail;
			if(t == null){
				Node h = new Node();//表示正在执行的线程节点，因此第一个线程来执行，不�?��建立队列
				h.next = node;
				node.prev = h;
				if(compareAndSetHead(h)){//这里会发生并发，�?��用cas操作
					tail = node;
					return node;
				}
			}else{
				if(compareAndSetTail(t, node)){
					t.next = node;
					node.prev = t;
					tail = node;
					return node;
				}
			}
		}
	}
	
	private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }

	
	protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }
	
	
	static final  class Node{
		
		static final int SIGNAL    = -1;
		
        static final int CANCELLED =  1;

        
        static final Node EXCLUSIVE = null;
        
		volatile Node prev;
		
		volatile Node next;
		
		volatile Thread thread;
		
		volatile int waitStatus;
		 
        Node nextWaiter;

		final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
	}
	
	
	protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
	
	 private void unparkSuccessor(Node node) {
		 Node next = node.next;
		 LockSupport.unpark(next.thread);
	 }
	
	/**
	 * 释放锁结构
	 * @param arg
	 * @return
	 */
	 public final boolean release(int arg) {
		 if(tryRelease(arg)){
			 Node h = head;
			 if(h.waitStatus != 0 && h!=null){
				 unparkSuccessor(h);
				 return true;
			 }
		 }
		 return true;
	 }
	
	 
	 
	 private static final Unsafe unsafe = UnsafeUtil.getUnsafe();
	    private static final long stateOffset;
	    private static final long headOffset;
	    private static final long tailOffset;
	    private static final long waitStatusOffset;
	    private static final long nextOffset;
	    static {
	        try {
	            stateOffset = unsafe.objectFieldOffset
	                (AbstractQueuedSynchronizer.class.getDeclaredField("state"));
	            headOffset = unsafe.objectFieldOffset
	                (AbstractQueuedSynchronizer.class.getDeclaredField("head"));
	            tailOffset = unsafe.objectFieldOffset
	                (AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
	            waitStatusOffset = unsafe.objectFieldOffset
	                (Node.class.getDeclaredField("waitStatus"));
	            nextOffset = unsafe.objectFieldOffset
	                (Node.class.getDeclaredField("next"));

	        } catch (Exception ex) { throw new Error(ex); }
	    }
	    
	    protected final boolean compareAndSetState(int expect, int update) {
	        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
	    }
	    
	    private final boolean compareAndSetHead(Node update) {
	    	return unsafe.compareAndSwapObject(this, headOffset, null, update);
	    }
	    
	    private final boolean compareAndSetTail(Node expect, Node update) {
	        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
	    }

	    private static final boolean compareAndSetWaitStatus(Node node,
	                                                         int expect,
	                                                         int update) {
	        return unsafe.compareAndSwapInt(node, waitStatusOffset,
	                                        expect, update);
	    }

	    private static final boolean compareAndSetNext(Node node,
	                                                   Node expect,
	                                                   Node update) {
	        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
	    }
		
}
