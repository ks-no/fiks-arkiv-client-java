package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.Matrikkelnummer


class MatrikkelnummerBuilder {
    var kommunenummer: String? = null
        private set
    var gardsnummer: Int? = null
        private set
    var bruksnummer: Int? = null
        private set
    var festenummer: Int? = null
        private set
    var seksjonsnummer: Int? = null
        private set

    fun kommunenummer(kommunenummer: String) = apply { this.kommunenummer = kommunenummer }
    fun gardsnummer(gardsnummer: Int) = apply { this.gardsnummer = gardsnummer }
    fun bruksnummer(bruksnummer: Int) = apply { this.bruksnummer = bruksnummer }
    fun festenummer(festenummer: Int) = apply { this.festenummer =  festenummer }
    fun seksjonsnummer(seksjonsnummer: Int) = apply { this.seksjonsnummer = seksjonsnummer }

    fun build() : Matrikkelnummer {
        return Matrikkelnummer().also {
            it.kommunenummer = checkNotNull(kommunenummer) {"Kommunenummer er påkrevd for Matrikkelnummer"}
            it.gardsnummer = gardsnummer ?: throw IllegalStateException("Gardsnummer er påkrevd for Matrikkelnummer")
            it.bruksnummer = bruksnummer ?: throw IllegalStateException("Bruksnummer er påkrevd for Matrikkelnummer")
            it.festenummer = festenummer
            it.seksjonsnummer = seksjonsnummer
        }
    }
}
