package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Matrikkelnummer


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
