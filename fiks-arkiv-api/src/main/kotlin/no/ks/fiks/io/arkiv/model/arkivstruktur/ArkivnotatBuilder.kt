package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Arkivnotat
import java.time.ZonedDateTime

class ArkivnotatBuilder : IRegistrering {
    var dokumentetsDato: ZonedDateTime? = null
        private set
    var mottattDato: ZonedDateTime? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: ZonedDateTime? = null
        private set
    var offentlighetsvurdertDato: ZonedDateTime? = null
        private set
    var antallVedlegg: Long? = null
        private set
    var utlaantDato: ZonedDateTime? = null
        private set
    var utlaantTil: String? = null
        private set

    fun dokumentetsDato(dokumentetsDato: ZonedDateTime) = apply { this.dokumentetsDato = dokumentetsDato }

    override fun buildApiModel() : Arkivnotat {
        return Arkivnotat().also {
            it.dokumentetsDato = dokumentetsDato
        }
    }
}
