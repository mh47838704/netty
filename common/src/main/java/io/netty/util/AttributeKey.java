/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.util;

/**
 * Key which can be used to access {@link Attribute} out of the {@link AttributeMap}. Be aware that it is not be
 * possible to have multiple keys with the same name.
 *
 * @param <T>   the type of the {@link Attribute} which can be accessed via this {@link AttributeKey}.
 */
@SuppressWarnings("UnusedDeclaration") // 'T' is used only at compile time
public final class AttributeKey<T> extends AbstractConstant<AttributeKey<T>> {

    private static final ConstantPool<AttributeKey<Object>> pool = new ConstantPool<AttributeKey<Object>>() {
        @Override
        protected AttributeKey<Object> newConstant(int id, String name) {
            // 生成的该对象主要是作为容器的key，所以必须保证id是唯一的或者是id+name是唯一的
            // 本类中的唯一id有ConstantPool保证，参考ConstantPool类中的getOrCreate(String name)和nextId()
            // AttributeKey在AttributeMap中作为可以的时候，是以AttributeKey的id作为AttributeMap中的数组
            // 的索引使用的，参考AttributeMap的attr(AttributeKey<T> key)方法和index(key)方法
            return new AttributeKey<Object>(id, name);
        }
    };

    /**
     * Returns the singleton instance of the {@link AttributeKey} which has the specified {@code name}.
     */
    @SuppressWarnings("unchecked")
    public static <T> AttributeKey<T> valueOf(String name) {
        return (AttributeKey<T>) pool.valueOf(name);
    }

    /**
     * Returns {@code true} if a {@link AttributeKey} exists for the given {@code name}.
     */
    public static boolean exists(String name) {
        return pool.exists(name);
    }

    /**
     * Creates a new {@link AttributeKey} for the given {@code name} or fail with an
     * {@link IllegalArgumentException} if a {@link AttributeKey} for the given {@code name} exists.
     */
    @SuppressWarnings("unchecked")
    public static <T> AttributeKey<T> newInstance(String name) {
        return (AttributeKey<T>) pool.newInstance(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> AttributeKey<T> valueOf(Class<?> firstNameComponent, String secondNameComponent) {
        return (AttributeKey<T>) pool.valueOf(firstNameComponent, secondNameComponent);
    }

    private AttributeKey(int id, String name) {
        super(id, name);
    }
}
