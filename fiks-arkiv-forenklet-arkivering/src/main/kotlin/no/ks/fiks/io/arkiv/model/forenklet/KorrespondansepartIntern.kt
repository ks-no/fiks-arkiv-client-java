package no.ks.fiks.io.arkiv.model.forenklet

class KorrespondansepartIntern {
    var administrativEnhet: String? = null
        private set
    var referanseAdministrativEnhet: String? = null
        private set
    var saksbehandler: String? = null
        private set
    var referanseSaksbehandler: String? = null
        private set

    fun administrativEnhet(administrativEnhet: String) = apply { this.administrativEnhet = administrativEnhet }
    fun referanseAdministrativEnhet(referanseAdministrativEnhet: String) = apply { this.referanseAdministrativEnhet = referanseAdministrativEnhet }
    fun saksbehandler(saksbehandler: String) = apply { this.saksbehandler = saksbehandler }
    fun referanseSaksbehandler(referanseSaksbehandler: String) = apply { this.referanseSaksbehandler = referanseSaksbehandler }
}
