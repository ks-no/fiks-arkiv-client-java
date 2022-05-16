package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.KassasjonsvedtakType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Kassasjon
import java.time.LocalDate
import java.time.ZonedDateTime

class KassasjonBuilder {
    var kassasjonsvedtak: KassasjonsvedtakType? = null
        private set
    var kassasjonshjemmel: String? = null
        private set
    var bevaringstid: Long? = null
        private set
    var kassasjonsdato: LocalDate? = null
        private set

    fun kassasjonsvedtak(kassasjonsvedtak: KassasjonsvedtakType) = apply { this.kassasjonsvedtak = kassasjonsvedtak }
    fun kassasjonshjemmel(kassasjonshjemmel: String) = apply { this.kassasjonshjemmel = kassasjonshjemmel }
    fun bevaringstid(bevaringstid: Long) = apply { this.bevaringstid = bevaringstid }
    fun kassasjonsdato(kassasjonsdato: LocalDate) = apply { this.kassasjonsdato = kassasjonsdato }

    fun build(): Kassasjon {
        return Kassasjon().also {
            it.kassasjonsvedtak = kassasjonsvedtak?.value ?: throw IllegalStateException("Kassasjonsvedtak er påkrevd for Kassasjon")
            it.kassasjonshjemmel = kassasjonshjemmel
            it.bevaringstid = bevaringstid?.toBigInteger() ?: throw IllegalStateException("Bevaringstid er påkrevd for Kassasjon")
            it.kassasjonsdato = checkNotNull(kassasjonsdato) {"kassasjonsdato er påkrevd for Kassasjon"}
        }
    }
}
