package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.*
import java.time.LocalDate
import java.time.ZonedDateTime

class ArkivnotatBuilder : IRegistrering<Arkivnotat>() {

    var dokumentetsDato: LocalDate? = null
        private set
    var mottattDato: ZonedDateTime? = null
        private set
    var sendtDato: ZonedDateTime? = null
        private set
    var forfallsdato: LocalDate? = null
        private set
    var offentlighetsvurdertDato: LocalDate? = null
        private set
    var antallVedlegg: Int? = null
        private set
    var utlaantDato: LocalDate? = null
        private set
    var utlaantTil: String? = null
        private set
    var dokumentflyt: List<Dokumentflyt> = ArrayList()
        private set

    fun dokumentetsDato(dokumentetsDato: LocalDate) = apply { this.dokumentetsDato = dokumentetsDato }
    fun mottattDato(mottattDato: ZonedDateTime) = apply { this.mottattDato = mottattDato }
    fun sendtDato(sendtDato: ZonedDateTime) = apply { this.sendtDato = sendtDato }
    fun forfallsdato(forfallsdato: LocalDate) = apply { this.forfallsdato = forfallsdato }
    fun offentlighetsvurdertDato(offentlighetsvurdertDato: LocalDate) = apply { this.offentlighetsvurdertDato = offentlighetsvurdertDato }
    fun antallVedlegg(antallVedlegg: Int) = apply { this.antallVedlegg = antallVedlegg }
    fun utlaantDato(utlaantDato: LocalDate) = apply { this.utlaantDato = utlaantDato }
    fun utlaantTil(utlaantTil: String) = apply { this.utlaantTil = utlaantTil }
    fun dokumentflyt(dokumentflyt: List<Dokumentflyt>) = apply { this.dokumentflyt = dokumentflyt }

    override fun build() : Arkivnotat {
        return Arkivnotat().also {
            build(it)
            it.dokumentetsDato = dokumentetsDato
            it.mottattDato = mottattDato
            it.sendtDato = sendtDato
            it.forfallsdato = forfallsdato
            it.offentlighetsvurdertDato = offentlighetsvurdertDato
            it.antallVedlegg = antallVedlegg
            it.utlaantDato = utlaantDato
            it.utlaantTil = utlaantTil
            it.dokumentflyts.addAll(dokumentflyt)

        }
    }
}
