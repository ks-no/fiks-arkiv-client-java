package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.UtfoertKassasjon
import java.time.ZonedDateTime

open class UtfortKassasjonBuilder {
    var kassertDato: ZonedDateTime = ZonedDateTime.now()
        private set
    var kassertAv: String? = null
        private set

    fun kassertDato(kassertDato: ZonedDateTime) = apply { this.kassertDato = kassertDato }
    fun kassertAv(kassertAv: String) = apply { this.kassertAv = kassertAv }

    open fun build() : UtfoertKassasjon {
        return UtfoertKassasjon().also {
            it.kassertDato = kassertDato
            it.kassertAv = checkNotNull(kassertAv) {"KassertAv er påkrevd for UtfoertKassasjon"}
        }
    }
}
