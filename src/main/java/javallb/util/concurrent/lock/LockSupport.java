package javallb.util.concurrent.lock;

import sun.misc.Unsafe;
import sunllb.misc.UnsafeUtil;

public class LockSupport {

	private LockSupport() {
	}

	private static final Unsafe unsafe = UnsafeUtil.getUnsafe();

	private static final long parkBlockerOffset;

	static {
		try {
			parkBlockerOffset = unsafe.objectFieldOffset(java.lang.Thread.class.getDeclaredField("parkBlocker"));
		} catch (Exception ex) {
			throw new Error(ex);
		}
	}

	private static void setBlocker(Thread t, Object arg) {
		unsafe.putObject(t, parkBlockerOffset, arg);
	}

	public static void unpark(Thread thread) {
		if (thread != null)
			unsafe.unpark(thread);
	}

	public static void park(Object blocker) {
		Thread t = Thread.currentThread();
		setBlocker(t, blocker);
		unsafe.park(false, 0L);
		setBlocker(t, null);
	}
}
