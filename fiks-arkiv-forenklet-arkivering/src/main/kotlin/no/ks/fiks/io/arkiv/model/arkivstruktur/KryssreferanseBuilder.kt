package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.Kryssreferanse
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilRegistrering

class KryssreferanseBuilder {
    var referanseTilKlasse: String? = null
        private set
    var referanseTilMappe: ReferanseTilMappe? = null
        private set
    var referanseTilRegistrering: ReferanseTilRegistrering? = null
        private set
    var referanseTilDokumentbeskrivelse: String? = null
        private set


    fun referanseTilKlasse(referanseTilKlasse: String) = apply { this.referanseTilKlasse = referanseTilKlasse }
    fun referanseTilMappe(referanseTilMappe: ReferanseTilMappe) = apply { this.referanseTilMappe = referanseTilMappe }
    fun referanseTilRegistrering(referanseTilRegistrering: ReferanseTilRegistrering) = apply { this.referanseTilRegistrering = referanseTilRegistrering }
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
