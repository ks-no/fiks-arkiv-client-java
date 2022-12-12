package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.AdministrativEnhet
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksbehandler

class KorrespondansepartIntern {
    var administrativEnhet: AdministrativEnhet? = null
        private set
    var referanseAdministrativEnhet: String? = null
        private set
    var saksbehandler: Saksbehandler? = null
        private set
    var referanseSaksbehandler: String? = null
        private set

    fun administrativEnhet(administrativEnhet: AdministrativEnhet) = apply { this.administrativEnhet = administrativEnhet }
    fun referanseAdministrativEnhet(referanseAdministrativEnhet: String) = apply { this.referanseAdministrativEnhet = referanseAdministrativEnhet }
    fun saksbehandler(saksbehandler: Saksbehandler) = apply { this.saksbehandler = saksbehandler }
    fun referanseSaksbehandler(referanseSaksbehandler: String) = apply { this.referanseSaksbehandler = referanseSaksbehandler }
}
