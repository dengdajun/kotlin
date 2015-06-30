/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kotlin.reflect.jvm.internal

import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.IllegalPropertyAccessException
import kotlin.reflect.KMutableProperty2
import kotlin.reflect.KProperty2

open class KProperty2Impl<D, E, out R> : DescriptorBasedProperty, KProperty2<D, E, R>, KPropertyImpl<R> {
    constructor(container: KClassImpl<D>, name: String, signature: String) : super(container, name, signature)

    constructor(container: KClassImpl<D>, descriptor: PropertyDescriptor) : super(container, descriptor)

    override val name: String get() = descriptor.getName().asString()

    override val getter: Method get() = super<DescriptorBasedProperty>.getter!!

    override val field: Field? get() = null

    override fun get(receiver1: D, receiver2: E): R {
        try {
            @suppress("UNCHECKED_CAST")
            return getter.invoke(receiver1, receiver2) as R
        }
        catch (e: IllegalAccessException) {
            throw IllegalPropertyAccessException(e)
        }
    }
}


class KMutableProperty2Impl<D, E, R> : KProperty2Impl<D, E, R>, KMutableProperty2<D, E, R>, KMutablePropertyImpl<R> {
    constructor(container: KClassImpl<D>, name: String, signature: String) : super(container, name, signature)

    constructor(container: KClassImpl<D>, descriptor: PropertyDescriptor) : super(container, descriptor)

    override val setter: Method get() = super<KProperty2Impl>.setter!!

    override fun set(receiver1: D, receiver2: E, value: R) {
        try {
            setter.invoke(receiver1, receiver2, value)
        }
        catch (e: IllegalAccessException) {
            throw IllegalPropertyAccessException(e)
        }
    }
}
