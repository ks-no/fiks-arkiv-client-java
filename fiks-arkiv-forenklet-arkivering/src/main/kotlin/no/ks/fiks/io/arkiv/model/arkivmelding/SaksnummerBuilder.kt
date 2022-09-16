package no.ks.fiks.io.arkiv.model.arkivmelding

import no.ks.fiks.arkiv.v1.arkivstruktur.metadatakatalog.Saksnummer


class SaksnummerBuilder {
    var saksaar: Int? = null
        private set
    var sakssekvensnummer: Int? = null
        private set

    fun saksaar(saksaar: Int) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Int) = apply { this.sakssekvensnummer = sakssekvensnummer }

    fun build() : Saksnummer {
        return Saksnummer().also {
            it.saksaar = saksaar ?: throw IllegalArgumentException("Saksnummer krever saksaar")
            it.sakssekvensnummer = sakssekvensnummer ?: throw IllegalArgumentException("Saksnummer krever sakssekvensnummer")
        }
    }
}
