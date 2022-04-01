package no.ks.fiks.io.arkiv.model

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalposttype

class JournalPostTypeBuilder {
    var kode: String? = null
        private set
    var beskrivelse: String? = null
        private set

    fun kode(kode: String) = apply { this.kode = kode }
    fun beskrivelse(beskrivelse: String) = apply { this.beskrivelse = beskrivelse }

    fun build(): Journalposttype {
        return Journalposttype().also {
            it.kode = kode
            it.beskrivelse = beskrivelse
        }
    }
}
