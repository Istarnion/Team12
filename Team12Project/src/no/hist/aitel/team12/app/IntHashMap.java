package no.hist.aitel.team12.app;

import bak.pcj.list.IntArrayList;

/*
 * Copyright (c) 1998 - 2005 Versant Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Versant Corporation - initial API and implementation
 */


/**
 * Specialized HashMap mapping int to Object. This is a cut and paste of
 * java.util.HashMap with the key hardcoded as int and some non-required
 * functionality removed.
 * 
 * Slightly modified by Hallgeir @ Team12 to use generics as stored object value.
 * Renamed from IntObjectHashMap to IntHashMap
 */
public final class IntHashMap<T> {

	/**
	 * The default initial capacity - MUST be a power of two.
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * The maximum capacity, used if a higher value is implicitly specified
	 * by either of the constructors with arguments.
	 * MUST be a power of two <= 1<<30.
	 */
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	/**
	 * The load factor used when none specified in constructor.
	 */
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * The table, resized as necessary. Length MUST Always be a power of two.
	 */
	private transient Entry<T>[] table;

	/**
	 * The number of key-value mappings contained in this identity hash map.
	 */
	private transient int size;

	/**
	 * The next size value at which to resize (capacity * load factor).
	 *
	 * @serial
	 */
	private int threshold;

	/**
	 * The load factor for the hash table.
	 *
	 * @serial
	 */
	private final float loadFactor;
	
	/**
	 * Constructs an empty <tt>IntObjectHashMap</tt> with the specified initial
	 * capacity and load factor.
	 *
	 * @param initialCapacity The initial capacity.
	 * @param loadFactor      The load factor.
	 * @throws IllegalArgumentException if the initial capacity is negative
	 *                                  or the load factor is nonpositive.
	 */
	public IntHashMap(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " +
					initialCapacity);
		}
		if (initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: " +
					loadFactor);
		}

		// Find a power of 2 >= initialCapacity
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}

		this.loadFactor = loadFactor;
		threshold = (int)(capacity * loadFactor);
		table = new Entry[capacity];
	}

	/**
	 * Constructs an empty <tt>IntObjectHashMap</tt> with the specified initial
	 * capacity and the default load factor (0.75).
	 *
	 * @param initialCapacity the initial capacity.
	 * @throws IllegalArgumentException if the initial capacity is negative.
	 */
	public IntHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * Constructs an empty <tt>IntObjectHashMap</tt> with the default initial capacity
	 * (16) and the default load factor (0.75).
	 */
	public IntHashMap() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * Returns a string representation of this map.  The string representation
	 * consists of a list of key-value mappings in the order returned by the
	 * map's <tt>entrySet</tt> view's iterator, enclosed in braces
	 * (<tt>"{}"</tt>).  Adjacent mappings are separated by the characters
	 * <tt>", "</tt> (comma and space).  Each key-value mapping is rendered as
	 * the key followed by an equals sign (<tt>"="</tt>) followed by the
	 * associated value.  Keys and values are converted to strings as by
	 * <tt>String.valueOf(Object)</tt>.<p>
	 * <p/>
	 * This implementation creates an empty string buffer, appends a left
	 * brace, and iterates over the map's <tt>entrySet</tt> view, appending
	 * the string representation of each <tt>map.entry</tt> in turn.  After
	 * appending each entry except the last, the string <tt>", "</tt> is
	 * appended.  Finally a right brace is appended.  A string is obtained
	 * from the stringbuffer, and returned.
	 *
	 * @return a String representation of this map.
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		for (int i = 0; i < table.length; i++) {
			Entry<T> e = table[i];
			for (; e != null; e = e.next) {
				int key = e.key;
				Object value = e.getValue();
				buf.append(key + "=" + (value == this ? "(this Map)" : value));
			}
		}
		buf.append("}");
		return buf.toString();
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 *
	 * @return the number of key-value mappings in this map.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 *
	 * @return <tt>true</tt> if this map contains no key-value mappings.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the value to which the specified key is mapped in this identity
	 * hash map, or <tt>null</tt> if the map contains no mapping for this key.
	 * A return value of <tt>null</tt> does not <i>necessarily</i> indicate
	 * that the map contains no mapping for the key; it is also possible that
	 * the map explicitly maps the key to <tt>null</tt>. The
	 * <tt>containsKey</tt> method may be used to distinguish these two cases.
	 *
	 * @param key the key whose associated value is to be returned.
	 * @return the value to which this map maps the specified key, or
	 *         <tt>null</tt> if the map contains no mapping for this key.
	 */
	public T get(int key) {
		int i = key & (table.length - 1);
		Entry<T> e = table[i];
		while (true) {
			if (e == null) {
				return null;
			}
			if (key == e.key) {
				return e.value;
			}
			e = e.next;
		}
	}

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the
	 * specified key.
	 *
	 * @param key The key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified
	 *         key.
	 */
	public boolean containsKey(int key) {
		int i = key & (table.length - 1);
		Entry<T> e = table[i];
		while (e != null) {
			if (key == e.key) {
				return true;
			}
			e = e.next;
		}
		return false;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previously contained a mapping for this key, the old
	 * value is replaced.
	 *
	 * @param key   key with which the specified value is to be associated.
	 * @param value value to be associated with the specified key.
	 * @return previous value associated with specified key, or <tt>null</tt>
	 *         if there was no mapping for key.  A <tt>null</tt> return can
	 *         also indicate that the IntObjectHashMap previously associated
	 *         <tt>null</tt> with the specified key.
	 */
	public T put(int key, T value) {
		int i = key & (table.length - 1);
		for (Entry<T> e = table[i]; e != null; e = e.next) {
			if (key == e.key) {
				T oldValue = e.value;
				e.value = value;
				return oldValue;
			}
		}
		addEntry(key, value, i);
		return null;
	}

	/**
	 * This method is used instead of put by constructors and
	 * pseudoconstructors (clone, readObject).  It does not resize the table,
	 * check for comodification, etc.  It calls createEntry rather than
	 * addEntry.
	 */
	private void putForCreate(int key, T value) {
		int i = key & (table.length - 1);

		/**
		 * Look for preexisting entry for key.  This will never happen for
		 * clone or deserialize.  It will only happen for construction if the
		 * input Map is a sorted map whose ordering is inconsistent w/ equals.
		 */
		for (Entry<T> e = table[i]; e != null; e = e.next) {
			if (key == e.key) {
				e.value = value;
				return;
			}
		}
		createEntry(key, value, i);
	}

	/**
	 * Rehashes the contents of this map into a new array with a
	 * larger capacity.  This method is called automatically when the
	 * number of keys in this map reaches its threshold.
	 * <p/>
	 * If current capacity is MAXIMUM_CAPACITY, this method does not
	 * resize the map, but but sets threshold to Integer.MAX_VALUE.
	 * This has the effect of preventing future calls.
	 *
	 * @param newCapacity the new capacity, MUST be a power of two;
	 *                    must be greater than current capacity unless current
	 *                    capacity is MAXIMUM_CAPACITY (in which case value
	 *                    is irrelevant).
	 */
	private void resize(int newCapacity) {
		Entry<T>[] oldTable = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity == MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}

		Entry<T>[] newTable = new Entry[newCapacity];
		transfer(newTable);
		table = newTable;
		threshold = (int)(newCapacity * loadFactor);
	}

	/**
	 * Transfer all entries from current table to newTable.
	 */
	private void transfer(Entry<T>[] newTable) {
		Entry<T>[] src = table;
		int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++) {
			Entry<T> e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					Entry<T> next = e.next;
					int i = e.key & (newCapacity - 1);
					e.next = newTable[i];
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
	}

	/**
	 * Removes the mapping for this key from this map if present.
	 *
	 * @param key key whose mapping is to be removed from the map.
	 * @return previous value associated with specified key, or <tt>null</tt>
	 *         if there was no mapping for key.  A <tt>null</tt> return can
	 *         also indicate that the map previously associated <tt>null</tt>
	 *         with the specified key.
	 */
	public T remove(int key) {
		Entry<T> e = removeEntryForKey(key);
		return e == null ? null : e.value;
	}

	/**
	 * Removes and returns the entry associated with the specified key
	 * in the IntObjectHashMap.  Returns null if the IntObjectHashMap contains no mapping
	 * for this key.
	 */
	private Entry<T> removeEntryForKey(int key) {
		int i = key & (table.length - 1);
		Entry<T> prev = table[i];
		Entry<T> e = prev;

		while (e != null) {
			Entry<T> next = e.next;
			if (key == e.key) {
				size--;
				if (prev == e) {
					table[i] = next;
				} else {
					prev.next = next;
				}
				return e;
			}
			prev = e;
			e = next;
		}

		return e;
	}

	/**
	 * Removes all mappings from this map.
	 */
	public void clear() {
		Entry<T> tab[] = table;
		for (int i = 0; i < tab.length; i++) {
			tab[i] = null;
		}
		size = 0;
	}

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the
	 * specified value.
	 *
	 * @param value value whose presence in this map is to be tested.
	 * @return <tt>true</tt> if this map maps one or more keys to the
	 *         specified value.
	 */
	public boolean containsValue(T value) {
		Entry<T> tab[] = table;
		for (int i = 0; i < tab.length; i++) {
			for (Entry<T> e = tab[i]; e != null; e = e.next) {
				if (value.equals(e.value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gathers and returns a list of all keys in the HashMap
	 * 
	 * @return An IntArrayList containing all keys
	 * @author Hallgeir
	 */
	public IntArrayList getKeys() {
		IntArrayList keys = new IntArrayList(table.length);
		for(Entry<T> e : table) {
			if(e != null) {
				keys.add(e.key);
			}
		}
		
		return keys;
	}
	
	private static class Entry<T> {

		final int key;
		T value;
		Entry<T> next;

		/**
		 * Create new entry.
		 */
		public Entry(int k, T v, Entry<T> n) {
			value = v;
			next = n;
			key = k;
		}

		public T getValue() {
			return value;
		}

		public T setValue(T newValue) {
			T oldValue = value;
			value = newValue;
			return oldValue;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Entry)) {
				return false;
			}
			Entry<?> e = (Entry<?>)o;
			if (key == e.key) {
				if (value == e.value || (value != null && value.equals(e.value))) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int hashCode() {
			return key ^ (value == null ? 0 : value.hashCode());
		}

		@Override
		public String toString() {
			return key + "=" + getValue();
		}

	}

	/**
	 * Add a new entry with the specified key, value and hash code to
	 * the specified bucket.  It is the responsibility of this
	 * method to resize the table if appropriate.
	 * <p/>
	 * Subclass overrides this to alter the behavior of put method.
	 */
	private void addEntry(int key, T value, int bucketIndex) {
		table[bucketIndex] = new Entry<T>(key, value, table[bucketIndex]);
		if (size++ >= threshold) {
			resize(2 * table.length);
		}
	}

	/**
	 * Like addEntry except that this version is used when creating entries
	 * as part of Map construction or "pseudo-construction" (cloning,
	 * deserialization).  This version needn't worry about resizing the table.
	 * <p/>
	 * Subclass overrides this to alter the behavior of IntObjectHashMap(Map),
	 * clone, and readObject.
	 */
	private void createEntry(int key, T value, int bucketIndex) {
		table[bucketIndex] = new Entry<T>(key, value, table[bucketIndex]);
		size++;
	}
}