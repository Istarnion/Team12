/*
 *  Primitive Collections for Java.
 *  Copyright (C) 2002, 2003  S�ren Bak
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package bak.pcj.adapter;

import bak.pcj.Adapter;
import bak.pcj.ByteIterator;
import bak.pcj.CharCollection;
import bak.pcj.map.ByteKeyCharMap;
import bak.pcj.map.AbstractByteKeyCharMap;
import bak.pcj.map.ByteKeyCharMapIterator;
import bak.pcj.map.MapDefaults;
import bak.pcj.map.NoSuchMappingException;
import bak.pcj.set.ByteSet;
import bak.pcj.util.Exceptions;

import java.util.Map;
import java.util.Iterator;

/**
 *  This class represents adaptions of Java Collections Framework
 *  maps to primitive maps from byte values to char values.
 *  The adapter is implemented as a wrapper around the map. 
 *  Thus, changes to the underlying map are reflected by this
 *  map and vice versa.
 *
 *  <p>
 *  Adapters from JCF maps to primitive map will
 *  fail if the JCF collection contains <tt>null</tt> keys/values or
 *  keys/values of the wrong class. However, adapters are not fast
 *  failing in the case that the underlying map should
 *  contain illegal keys or values. To implement fast failure would require
 *  every operation to check every key and value of the underlying
 *  map before doing anything. Instead validation methods
 *  are provided. They can be called using the assertion facility
 *  in the client code:
 *  <pre>
 *      MapToByteKeyCharMapAdapter s;
 *      ...
 *      <b>assert</b> s.validate();
 *  </pre>
 *  or by letting the adapter throw an exception on illegal values:
 *  <pre>
 *      MapToByteKeyCharMapAdapter s;
 *      ...
 *      s.evalidate();  // Throws an exception on illegal values
 *  </pre>
 *  Either way, validation must be invoked directly by the client
 *  code.
 *
 *  @author     S&oslash;ren Bak
 *  @version    1.3     21-08-2003 19:09
 *  @since      1.0
 */
public class MapToByteKeyCharMapAdapter extends AbstractByteKeyCharMap implements ByteKeyCharMap {

    /** The underlying map. */
    protected Map map;

    /** The value corresponding to the last key found by containsKey(). */
    protected Character lastValue;

    /**
     *  Creates a new adaption to a map from byte
     *  values to char values.
     *
     *  @param      map
     *              the underlying map. This map must
     *              consist of keys of class
     *              {@link Byte Byte}.
     *              values of class
     *              {@link Character Character}. Otherwise a
     *              {@link ClassCastException ClassCastException}
     *              will be thrown by some methods.
     *
     *  @throws     NullPointerException
     *              if <tt>map</tt> is <tt>null</tt>.
     */
    public MapToByteKeyCharMapAdapter(Map map) {
        if (map == null)
            Exceptions.nullArgument("map");
        this.map = map;
        lastValue = null;
    }

    /**
     *  Creates a new adaption to a map from byte
     *  values to char values. The map to adapt is optionally validated.
     *
     *  @param      map
     *              the underlying map. This map must
     *              consist of keys of class
     *              {@link Byte Byte}.
     *              values of class
     *              {@link Character Character}. Otherwise a
     *              {@link ClassCastException ClassCastException}
     *              will be thrown by some methods.
     *
     *  @param      validate
     *              indicates whether <tt>map</tt> should
     *              be checked for illegal values.
     *
     *  @throws     NullPointerException
     *              if <tt>map</tt> is <tt>null</tt>.
     *
     *  @throws     IllegalStateException
     *              if <tt>validate</tt> is <tt>true</tt> and
     *              <tt>map</tt> contains a <tt>null</tt> key/value,
     *              a key that is not of class
     *              {@link Byte Byte},
     *              or a value that is not of class
     *              {@link Character Character}.
     */
    public MapToByteKeyCharMapAdapter(Map map, boolean validate) {
        if (map == null)
            Exceptions.nullArgument("map");
        this.map = map;
        lastValue = null;
        if (validate)
            evalidate();
    }

    public void clear()
    { map.clear(); }

    public boolean containsKey(byte key) {
        lastValue = (Character)map.get(new Byte(key));
        return lastValue != null;
    }

    public boolean containsValue(char value)
    { return map.containsValue(new Character(value)); }

    public ByteKeyCharMapIterator entries() {
        return new ByteKeyCharMapIterator() {
            Iterator i = map.entrySet().iterator();
            Map.Entry lastEntry = null;

            public boolean hasNext()
            { return i.hasNext(); }

            public void next()
            { lastEntry = (Map.Entry)i.next(); }

            public byte getKey() {
                if (lastEntry == null)
                    Exceptions.noElementToGet();
                return ((Byte)lastEntry.getKey()).byteValue();
            }

            public char getValue() {
                if (lastEntry == null)
                    Exceptions.noElementToGet();
                return ((Character)lastEntry.getValue()).charValue();
            }

            public void remove() {
                i.remove();
                lastEntry = null;
            }
        };
    }

    public char get(byte key) {
        Character value = (Character)map.get(new Byte(key));
        return value == null ? MapDefaults.defaultChar() : value.charValue();
    }

    public ByteSet keySet()
    { return new SetToByteSetAdapter(map.keySet()); }

    public char lget() {
        if (lastValue == null)
            Exceptions.noLastElement();
        return lastValue.charValue();
    }

    public char put(byte key, char value) {
        Character oldValue = (Character)map.put(new Byte(key), new Character(value));
        return oldValue == null ? MapDefaults.defaultChar() : oldValue.charValue();
    }

    public char remove(byte key) {
        Character value = (Character)map.remove(new Byte(key));
        return value == null ? MapDefaults.defaultChar() : value.charValue();
    }

    public int size()
    { return map.size(); }

    public CharCollection values()
    { return new CollectionToCharCollectionAdapter(map.values()); }

    public char tget(byte key) {
        Character value = (Character)map.get(new Byte(key));
        if (value == null)
            Exceptions.noSuchMapping(String.valueOf(key));
        return value.charValue();
    }

    /**
     *  Indicates whether the underlying map is valid for
     *  this adapter. For the underlying map to be valid, it
     *  can only contain {@link Byte Byte} keys, no <tt>null</tt>
     *  keys/values, and only {@link Character Character} values.
     *
     *  @return     <tt>true</tt> if the underlying map is
     *              valid; returns <tt>false</tt> otherwise.
     */
    public boolean validate()
    { return Adapter.isByteKeyCharAdaptable(map); }

    /**
     *  Validates the map underlying this adapter and throws
     *  an exception if it is invalid. For the underlying map 
     *  to be valid, it
     *  can only contain {@link Byte Byte} keys, no <tt>null</tt>
     *  keys/values, and only {@link Character Character} values.
     *
     *  @throws     IllegalStateException
     *              if the underlying map is invalid.
     */
    public void evalidate() {
        if (!validate())
            Exceptions.cannotAdapt("map");
    }

}