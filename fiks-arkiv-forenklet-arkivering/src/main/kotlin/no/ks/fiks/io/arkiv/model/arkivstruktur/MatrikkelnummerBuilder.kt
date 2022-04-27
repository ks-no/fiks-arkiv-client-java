package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.GradType
import no.ks.fiks.io.arkiv.v1.client.models.arkivmelding.Matrikkelnummer
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Gradering
import java.time.ZonedDateTime

class MatrikkelnummerBuilder {
    var kommunenummer: String? = null
        private set
    var gardsnummer: Long? = null
        private set
    var bruksnummer: Long? = null
        private set
    var festenummer: Long? = null
        private set
    var seksjonsnummer: Long? = null
        private set

    fun kommunenummer(kommunenummer: String) = apply { this.kommunenummer = kommunenummer }
    fun gardsnummer(gardsnummer: Long) = apply { this.gardsnummer = gardsnummer }
    fun bruksnummer(bruksnummer: Long) = apply { this.bruksnummer = bruksnummer }
    fun festenummer(festenummer: Long) = apply { this.festenummer =  festenummer }
    fun seksjonsnummer(seksjonsnummer: Long) = apply { this.seksjonsnummer = seksjonsnummer }

    fun build() : Matrikkelnummer {
        return Matrikkelnummer().also {
            it.kommunenummer = checkNotNull(kommunenummer) {"Kommunenummer er påkrevd for Matrikkelnummer"}
            it.gardsnummer = gardsnummer?.toBigInteger() ?: throw IllegalStateException("Gardsnummer er påkrevd for Matrikkelnummer")
            it.bruksnummer = bruksnummer?.toBigInteger() ?: throw IllegalStateException("Bruksnummer er påkrevd for Matrikkelnummer")
            it.festenummer = festenummer?.toBigInteger()
            it.seksjonsnummer = seksjonsnummer?.toBigInteger()
        }
    }
}
