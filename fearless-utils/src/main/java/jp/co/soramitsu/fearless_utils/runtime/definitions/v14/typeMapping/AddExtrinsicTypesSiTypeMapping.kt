package jp.co.soramitsu.fearless_utils.runtime.definitions.v14.typeMapping

import jp.co.soramitsu.fearless_utils.runtime.definitions.registry.TypePresetBuilder
import jp.co.soramitsu.fearless_utils.runtime.definitions.registry.alias
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.Type
import jp.co.soramitsu.fearless_utils.runtime.definitions.types.instances.ExtrinsicTypes
import jp.co.soramitsu.fearless_utils.runtime.metadata.v14.PortableType
import jp.co.soramitsu.fearless_utils.runtime.metadata.v14.paramType
import jp.co.soramitsu.fearless_utils.runtime.metadata.v14.type
import jp.co.soramitsu.fearless_utils.scale.EncodableStruct

private const val UNCHECKED_EXTRINSIC_TYPE = "sp_runtime.generic.unchecked_extrinsic.UncheckedExtrinsic"

class AddExtrinsicTypesSiTypeMapping : SiTypeMapping {

    override fun map(
        originalDefinition: EncodableStruct<PortableType>,
        suggestedTypeName: String,
        typesBuilder: TypePresetBuilder
    ): Type<*>? {
        if (suggestedTypeName == UNCHECKED_EXTRINSIC_TYPE) {
            addTypeFromTypeParams(
                originalDefinition = originalDefinition,
                typesBuilder = typesBuilder,
                typeParamName = "Address",
                newTypeName = ExtrinsicTypes.ADDRESS
            )

            addTypeFromTypeParams(
                originalDefinition = originalDefinition,
                typesBuilder = typesBuilder,
                typeParamName = "Signature",
                newTypeName = ExtrinsicTypes.SIGNATURE
            )
        }

        // we don't modify any existing type
        return null
    }

    private fun addTypeFromTypeParams(
        originalDefinition: EncodableStruct<PortableType>,
        typesBuilder: TypePresetBuilder,
        typeParamName: String,
        newTypeName: String
    ) {
        val paramType = originalDefinition.type.paramType(typeParamName) ?: return

        // type with type-id name is present in the registry as alias to fully qualified name
        typesBuilder.alias(newTypeName, paramType.toString())
    }
}
