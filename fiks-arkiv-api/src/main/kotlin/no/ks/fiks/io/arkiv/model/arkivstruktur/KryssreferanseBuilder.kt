package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Kryssreferanse

class KryssreferanseBuilder {
    var referanseTilKlasse: String? = null
        private set
    var referanseTilMappe: String? = null
        private set
    var referanseTilRegistrering: String? = null
        private set
    var referanseTilDokumentbeskrivelse: String? = null
        private set


    fun referanseTilKlasse(referanseTilKlasse: String) = apply { this.referanseTilKlasse = referanseTilKlasse }
    fun referanseTilMappe(referanseTilMappe: String) = apply { this.referanseTilMappe = referanseTilMappe }
    fun referanseTilRegistrering(referanseTilRegistrering: String) = apply { this.referanseTilRegistrering = referanseTilRegistrering }
    fun referanseTilDokumentbeskrivelse(referanseTilDokumentbeskrivelse: String) = apply { this.referanseTilDokumentbeskrivelse = referanseTilDokumentbeskrivelse }

    fun build() : Kryssreferanse {
        return Kryssreferanse().also {
            it.referanseTilKlasse = referanseTilKlasse
            it.referanseTilMappe = referanseTilMappe
            it.referanseTilRegistrering = referanseTilRegistrering
            it.referanseTilDokumentbeskrivelse = checkNotNull(referanseTilDokumentbeskrivelse) {"ReferanseTilDokumentbeskrivelse er påkrevd for Kryssreferanse"}
        }
    }
}
