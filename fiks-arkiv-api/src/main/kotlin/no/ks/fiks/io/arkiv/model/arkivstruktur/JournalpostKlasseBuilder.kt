package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.arkivverket.standarder.noark5.metadatakatalog.v2.Journalposttype
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Klasse

class JournalpostKlasseBuilder: KlasseBuilder() {
    var journalposter: List<JournalpostBuilder> = ArrayList()
        private set

    fun journalposter(journalposter: List<JournalpostBuilder>) = apply { this.journalposter = journalposter }

    override fun build(): Klasse {
        return super.build().also {
            it.registrerings.addAll(journalposter.map { j -> j.buildApiModel() }.toList())
        }
    }
}