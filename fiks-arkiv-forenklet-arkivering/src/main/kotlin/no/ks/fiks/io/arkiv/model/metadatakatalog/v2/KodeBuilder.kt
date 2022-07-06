package no.ks.fiks.io.arkiv.model.metadatakatalog.v2

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Kode
import java.util.*

class KodeBuilder {
    /**
     * @param value Globalt unik identifikasjon av arkivenheten (UID).
     * @see SystemID
     */
    var kode: String? = null
        private set
    var beskrivelse: String? = null
        private set

    fun kode(kode: String) = apply { this.kode = kode }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }

    fun build(): Kode {
        return Kode().also {
            it.kode = checkNotNull(kode) {"Value må være definert for SystemID"}
            it.beskrivelse = beskrivelse
        }
    }
}

