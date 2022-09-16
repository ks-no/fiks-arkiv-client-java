package no.ks.fiks.io.arkiv.model.forenklet

import no.ks.fiks.io.arkiv.model.arkivstruktur.AdministrativEnhetBuilder
import no.ks.fiks.io.arkiv.model.arkivstruktur.SaksbehandlerBuilder

class KorrespondansepartIntern {
    var administrativEnhet: AdministrativEnhetBuilder? = null
        private set
    var referanseAdministrativEnhet: String? = null
        private set
    var saksbehandler: SaksbehandlerBuilder? = null
        private set
    var referanseSaksbehandler: String? = null
        private set

    fun administrativEnhet(administrativEnhet: AdministrativEnhetBuilder) = apply { this.administrativEnhet = administrativEnhet }
    fun referanseAdministrativEnhet(referanseAdministrativEnhet: String) = apply { this.referanseAdministrativEnhet = referanseAdministrativEnhet }
    fun saksbehandler(saksbehandler: SaksbehandlerBuilder) = apply { this.saksbehandler = saksbehandler }
    fun referanseSaksbehandler(referanseSaksbehandler: String) = apply { this.referanseSaksbehandler = referanseSaksbehandler }
}
