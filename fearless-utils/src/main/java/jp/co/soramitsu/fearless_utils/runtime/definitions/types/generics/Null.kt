package jp.co.soramitsu.fearless_utils.runtime.definitions.types.generics

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import jp.co.soramitsu.fearless_utils.runtime.RuntimeSnapshot
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.RuntimeType
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.primitives.Primitive
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.skipAliases

object Null : Primitive<Any?>("Null") {

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): Any? {
        return null
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: Any?) {
        // pass
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance == null
    }
}

fun RuntimeType<*, *>.isNullType(): Boolean {
    return skipAliases() is Null
}
