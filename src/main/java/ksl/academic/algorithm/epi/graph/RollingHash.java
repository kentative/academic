package ksl.academic.algorithm.epi.graph;

import java.math.BigInteger;
import java.util.Random;

public class RollingHash {
	static private long dochash = -1L;

	private int radix;
	private int M; // pattern length
	private long RM; // R^(M-1) % Q
	private int Q; // a large prime, small enough to avoid long overflow

	public RollingHash(int R, char[] pattern) {
		throw new RuntimeException("Operation not supported yet");
	}

	public RollingHash(int size) {
		this.M = size;
		radix = 29;
		
		Q = BigInteger.probablePrime(31, new Random()).intValue();

		// precompute R^(M-1) % Q for use in removing leading digit
		RM = 1;
		for (int i = 1; i <= M - 1; i++)
			RM = (radix * RM) % Q;
	}

	// Compute hash for pattern[0..M-1].
	private long hash(String pattern, int M) {
		long h = 0;
		for (int j = 0; j < M; j++)
			h = (radix * h + pattern.charAt(j)) % Q;
		return h;
	}

	// check for exact match
	public int search(String text, String pattern) {
		int N = text.length();
		if (N < M) return -1;
		
		long txtHash;
		if (dochash == -1L) {
			txtHash = hash(text, M);
			dochash = txtHash;
		} else
			txtHash = dochash;

		// check for match at offset 0
		long patHash = hash(pattern, pattern.length());
		if ((patHash == txtHash) && pattern.equals(text.substring(0,  M))) return 0;

		// check for hash match; if hash match, check for exact match
		for (int i = M; i < N; i++) {
			// Remove leading digit, add trailing digit, check for match.
			txtHash = (txtHash + Q - RM * text.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * radix + text.charAt(i)) % Q;

			// match
			int offset = i - M + 1;
			if ((patHash == txtHash) && pattern.equals(text.substring(offset,  offset+M))) return offset;
		}

		// no match
		return -1; // was N
	}

}
