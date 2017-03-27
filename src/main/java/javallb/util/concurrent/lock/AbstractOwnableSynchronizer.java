package javallb.util.concurrent.lock;

public abstract class AbstractOwnableSynchronizer implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -629291489324509467L;
	
    protected AbstractOwnableSynchronizer() { }

    
    
	 private transient Thread exclusiveOwnerThread;

	 protected final Thread getExclusiveOwnerThread() {
		return exclusiveOwnerThread;
	}


	/**
	 * 此方法之所以不是同步  说明这个方法的执行都是在安全状态下执行的
	 * @param exclusiveOwnerThread
	 */
	protected final void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
		this.exclusiveOwnerThread = exclusiveOwnerThread;
	}
	
}
