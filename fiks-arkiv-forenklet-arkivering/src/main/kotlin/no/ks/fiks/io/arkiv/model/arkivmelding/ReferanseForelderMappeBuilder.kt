package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivmelding.ReferanseForelderMappe
import no.ks.fiks.io.arkiv.model.arkivstruktur.EksternNoekkelBuilder
import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.SystemIDBuilder

class ReferanseForelderMappeBuilder {
    var systemID: SystemIDBuilder? = null
        private set
    var referanseEksternNoekkel: EksternNoekkelBuilder? = null
        private set
    var saksnummer: SaksnummerBuilder? = null
        private set

    fun systemID(systemID: SystemIDBuilder) = apply { if(referanseEksternNoekkel == null && saksnummer == null) this.systemID = systemID else throw IllegalArgumentException("Det er ikke mulig å registrere både systemID og referanseEksternNoekkel eller saksnummer på ReferanseForelderMappe") }
    fun referanseEksternNoekkel(referanseEksternNoekkel: EksternNoekkelBuilder) = apply { if(systemID == null && saksnummer == null) this.referanseEksternNoekkel = referanseEksternNoekkel else throw IllegalArgumentException("Det er ikke mulig å registrere både referanseEksternNoekkel og systemID eller saksnummer på ReferanseForelderMappe") }
    fun saksnummer(saksnummer: SaksnummerBuilder) = apply { if(systemID == null && referanseEksternNoekkel == null) this.saksnummer = saksnummer else throw IllegalArgumentException("Det er ikke mulig å registrere både saksnummer og referanseEksternNoekkel eller systemID på ReferanseForelderMappe") }

    fun build() : ReferanseForelderMappe {
        return ReferanseForelderMappe().also {
            it.systemID = systemID?.build()
            it.referanseEksternNoekkel = referanseEksternNoekkel?.build()
            it.saksnummer = saksnummer?.build()
        }
    }
}
