package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivstruktur.Sletting
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SlettingsType
import java.time.ZonedDateTime

open class SlettingBuilder {
    var slettingsType: SlettingsType? = null
        private set
    var slettetDato: ZonedDateTime? = null
        private set
    var slettetAv: String? = null
        private set

    fun slettingsType(slettingsType: SlettingsType) = apply { this.slettingsType = slettingsType }
    fun slettetDato(slettetDato: ZonedDateTime) = apply { this.slettetDato = slettetDato }
    fun slettetAv(slettetAv: String) = apply { this.slettetAv = slettetAv }

    open fun build() : Sletting {
        return Sletting().also {
            it.slettingstype = slettingsType?.value ?: throw IllegalStateException("Slettingstype er påkrevd for Sletting")
            it.slettetDato = checkNotNull(slettetDato) {"SlettetDato er påkrevd for Sletting"}
            it.slettetAv = checkNotNull(slettetAv) {"SlettetAv er påkrevd for Slettet"}
        }
    }
}
