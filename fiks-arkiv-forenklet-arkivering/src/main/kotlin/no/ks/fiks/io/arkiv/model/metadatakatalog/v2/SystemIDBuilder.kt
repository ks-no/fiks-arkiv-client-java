package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID
import java.util.*

class SystemIDBuilder {
    /**
     * @param value Globalt unik identifikasjon av arkivenheten (UID).
     * @see SystemID
     */
    var value: UUID? = null
        private set
    var label: String? = null
        private set

    fun value(value: UUID) = apply { this.value = value }
    fun label(label: String) = apply { this.label = label }

    fun build(): SystemID {
        return SystemID().also {
            it.value = value?.toString() ?: throw IllegalStateException("Value må være definert for SystemID")
            it.label = label
        }
    }
}

