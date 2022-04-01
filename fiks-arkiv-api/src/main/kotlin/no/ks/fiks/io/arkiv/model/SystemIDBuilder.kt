package no.ks.fiks.io.arkiv.model

import no.arkivverket.standarder.noark5.metadatakatalog.v2.SystemID
import java.util.*

class SystemIDBuilder {
    var value: UUID? = null
        private set
    var label: String? = null
        private set

    fun value(value: UUID) = apply { this.value = value }
    fun label(label: String) = apply { this.label = label }

    fun buildApiModel(): SystemID {
        return SystemID().also {
            it.value = value.toString()
            it.label = label
        }
    }
}

