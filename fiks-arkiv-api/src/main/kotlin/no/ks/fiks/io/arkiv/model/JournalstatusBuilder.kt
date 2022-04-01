package no.ks.fiks.io.arkiv.model

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalstatus

class JournalstatusBuilder {
    var kode: String? = null
        private set
    var beskrivelse: String? = null
        private set

    fun kode(kode: String) = apply { this.kode = kode }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }

    fun build(): Journalstatus {
        return Journalstatus().also {
            it.kode = kode
            it.beskrivelse = beskrivelse
        }
    }
}
