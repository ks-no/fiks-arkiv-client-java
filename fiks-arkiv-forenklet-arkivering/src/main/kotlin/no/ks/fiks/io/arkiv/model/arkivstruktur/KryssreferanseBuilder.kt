package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.arkiv.v1.arkivmelding.opprett.Kryssreferanse
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilDokumentbeskrivelse
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilRegistrering

class KryssreferanseBuilder {
    var referanseTilKlasse: String? = null
        private set
    var referanseTilMappe: ReferanseTilMappe? = null
        private set
    var referanseTilRegistrering: ReferanseTilRegistrering? = null
        private set
    var referanseTilDokumentbeskrivelse: ReferanseTilDokumentbeskrivelse? = null
        private set

    fun referanseTilMappe(referanseTilMappe: ReferanseTilMappe) = apply { this.referanseTilMappe = referanseTilMappe }
    fun referanseTilRegistrering(referanseTilRegistrering: ReferanseTilRegistrering) = apply { this.referanseTilRegistrering = referanseTilRegistrering }
    fun referanseTilDokumentbeskrivelse(referanseTilDokumentbeskrivelse: ReferanseTilDokumentbeskrivelse) = apply { this.referanseTilDokumentbeskrivelse = referanseTilDokumentbeskrivelse }

    fun build() : Kryssreferanse {
        return Kryssreferanse().also {
            it.referanseTilMappe = referanseTilMappe
            it.referanseTilRegistrering = referanseTilRegistrering
            it.referanseTilDokumentbeskrivelse = checkNotNull(referanseTilDokumentbeskrivelse) {"ReferanseTilDokumentbeskrivelse er påkrevd for Kryssreferanse"}
        }
    }
}
