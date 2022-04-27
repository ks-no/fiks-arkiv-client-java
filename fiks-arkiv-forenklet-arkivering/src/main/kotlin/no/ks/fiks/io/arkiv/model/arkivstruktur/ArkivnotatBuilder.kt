package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Arkivnotat
import java.time.ZonedDateTime

class ArkivnotatBuilder {
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
    var dokumentflyt: List<DokumentflytBuilder> = ArrayList()
        private set

    fun dokumentetsDato(dokumentetsDato: ZonedDateTime) = apply { this.dokumentetsDato = dokumentetsDato }
    fun mottattDato(mottattDato: ZonedDateTime) = apply { this.mottattDato = mottattDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun forfallsdato(forfallsdato: ZonedDateTime) = apply { this.forfallsdato = forfallsdato }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: ZonedDateTime) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun antallVedlegg(antallVedlegg: Long) = apply { this.antallVedlegg = antallVedlegg }
    fun utlaantDato(utlaantDato: ZonedDateTime) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun dokumentflyt(dokumentflyt: List<DokumentflytBuilder>) = apply { this.dokumentflyt = dokumentflyt }

    fun build() : Arkivnotat {
        return Arkivnotat().also {
            it.dokumentetsDato = dokumentetsDato
            it.mottattDato = mottattDato
            it.sendtDato = sendtDato
            it.forfallsdato = forfallsdato
            it.offentlighetsvurdertDato = offentlighetsvurdertDato
            it.antallVedlegg = antallVedlegg?.toBigInteger()
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.dokumentflyts.addAll( dokumentflyt.map { d -> d.build() }.toList() )
        }
    }
}
