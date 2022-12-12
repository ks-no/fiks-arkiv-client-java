package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.EksternNoekkel
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.ReferanseTilMappe
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksnummer
import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.SystemID

class ReferanseTilMappeBuilder {
    var systemID: SystemID? = null
        private set
    var referanseEksternNoekkel: EksternNoekkel? = null
        private set
    var saksnummer: Saksnummer? = null
        private set

    fun systemID(systemID: SystemID) = apply { if(referanseEksternNoekkel == null && saksnummer == null) this.systemID = systemID else throw IllegalArgumentException("Det er ikke mulig å registrere både systemID og referanseEksternNoekkel eller saksnummer på ReferanseForelderMappe") }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkel) = apply { if(systemID == null && saksnummer == null) this.referanseEksternNoekkel = referanseEksternNoekkel else throw IllegalArgumentException("Det er ikke mulig å registrere både referanseEksternNoekkel og systemID eller saksnummer på ReferanseForelderMappe") }
    fun saksnummer(saksnummer: Saksnummer) = apply { if(systemID == null && referanseEksternNoekkel == null) this.saksnummer = saksnummer else throw IllegalArgumentException("Det er ikke mulig å registrere både saksnummer og referanseEksternNoekkel eller systemID på ReferanseForelderMappe") }

    fun build() : ReferanseTilMappe {
        return ReferanseTilMappe().also {
            it.systemID = systemID
            it.referanseEksternNoekkel = referanseEksternNoekkel
            it.saksnummer = saksnummer
        }
    }
}
