package jp.co.soramitsu.fearless_utils.runtime.definitions.types.instances

import jp.co.soramitsu.fearless_utils.runtime.AccountId
import jp.co.soramitsu.fearless_utils.runtime.definitions.registry.TypeRegistry
import jp.co.soramitsu.fearless_utils.runtime.definitions.registry.getOrThrow
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.RuntimeType
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.composite.DictEnum
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.generics.MULTI_ADDRESS_ID
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.primitives.FixedByteArray

object AddressInstanceConstructor : RuntimeType.InstanceConstructor<AccountId> {

    override fun constructInstance(typeRegistry: TypeRegistry, value: AccountId): Any {
        return when (val addressType = typeRegistry.getOrThrow(ExtrinsicTypes.ADDRESS)) {
            is DictEnum -> { // MultiAddress
                DictEnum.Entry(MULTI_ADDRESS_ID, value)
            }
            is FixedByteArray -> { // GenericAccountId or similar
                value
            }
            else -> throw UnsupportedOperationException("Unknown address type: ${addressType.name}")
        }
    }
}
