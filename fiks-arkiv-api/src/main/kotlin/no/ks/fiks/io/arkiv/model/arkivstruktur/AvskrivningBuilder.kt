package no.ks.fiks.io.arkiv.model.arkivstruktur

import no.ks.fiks.io.arkiv.model.metadatakatalog.v2.AvskrivningsmaateType
import no.ks.fiks.io.arkiv.v1.client.models.arkivstruktur.Avskrivning
import java.time.ZonedDateTime

class AvskrivningBuilder {
    var avskrivningsdato: ZonedDateTime = ZonedDateTime.now()
        private set
    var avskrevetAv: String? = null
        private set
    var avskrivningsmaate: AvskrivningsmaateType? = null
        private set
    var referanseAvskrivesAvJournalpost: String? = null
        private set

    fun avskrivningsdato(avskrivningsdato: ZonedDateTime) = apply { this.avskrivningsdato = avskrivningsdato }
    fun avskrevetAv(avskrevetAv: String) = apply { this.avskrevetAv = avskrevetAv }
    fun avskrivningsmaate(avskrivningsmaate: AvskrivningsmaateType) = apply { this.avskrivningsmaate =  avskrivningsmaate }
    fun referanseAvskrivesAvJournalpost(referanseAvskrivesAvJournalpost: String) = apply { this.referanseAvskrivesAvJournalpost = referanseAvskrivesAvJournalpost }

    fun build(): Avskrivning {
        return Avskrivning().also {
            it.avskrivningsdato = avskrivningsdato
            it.avskrevetAv = checkNotNull(avskrevetAv) { "avskrevetAv er påkrevd for Avskrivning" }
            it.avskrivningsmaate = avskrivningsmaate?.value ?: throw IllegalStateException("Avskrivningsmaate er påkrevd for Avskrivning")
            it.referanseAvskrivesAvJournalpost = referanseAvskrivesAvJournalpost
        }
    }
}
