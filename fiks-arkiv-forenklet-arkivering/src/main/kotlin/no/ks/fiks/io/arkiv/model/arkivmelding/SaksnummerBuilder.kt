package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksnummer


class SaksnummerBuilder {
    var saksaar: Long? = null
        private set
    var sakssekvensnummer: Long? = null
        private set

    fun saksaar(saksaar: Long) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Long) = apply { this.sakssekvensnummer = sakssekvensnummer }

    fun build() : Saksnummer {
        return Saksnummer().also {
            it.saksaar = saksaar?.toBigInteger()
            it.sakssekvensnummer = sakssekvensnummer?.toBigInteger()
        }
    }
}
