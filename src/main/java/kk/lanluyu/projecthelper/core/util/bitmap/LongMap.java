package kk.lanluyu.projecthelper.core.util.bitmap;

import java.io.Serializable;

/**
 * 过滤器BitMap在64位机器上.这个类能发生更好的效果.一般机器不建议使用
 *
 * @author loolly
 *
 */
public class LongMap implements BitMap, Serializable {
	private static final long serialVersionUID = 1L;

	private final long[] longs;

	/**
	 * 构造
	 */
	public LongMap() {
		longs = new long[93750000];
	}

	/**
	 * 构造
	 *
	 * @param size 容量
	 */
	public LongMap(int size) {
		longs = new long[size];
	}

	@Override
	public void add(long i) {
		int r = (int) (i / MACHINE64);
		long c = i & (MACHINE64 - 1);
		longs[r] = longs[r] | (1L << c);
	}

	@Override
	public boolean contains(long i) {
		int r = (int) (i / MACHINE64);
		long c = i & (MACHINE64 - 1);
		return ((longs[r] >>> c) & 1) == 1;
	}

	@Override
	public void remove(long i) {
		int r = (int) (i / MACHINE64);
		long c = i & (MACHINE64 - 1);
		longs[r] &= ~(1L << c);
	}

}
